package za.co.authoritativelabpro.rest;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import za.co.authoritativelabpro.api.ItemManager;
import za.co.authoritativelabpro.converter.TypeConverter;
import za.co.authoritativelabpro.model.Item;

@Path("/")
@RequestScoped
public class ItemRestService {
    
    @Inject
    ItemManager		 itemManager;
    
    private static final Logger log = Logger.getLogger(ItemRestService.class);
    
    @Path("getItems")
    @GET
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getItems() {
	log.info("getItems");
	return itemManager.getItems();
    }
    
    @POST
    @Path("createItem")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItem(String items) {
	
	Response.ResponseBuilder builder = null;
	
	TypeConverter<Item> converter = new TypeConverter<Item>(Item.class);
	
	List<Item> itemList = converter.convert(items);
	
	for (Item item : itemList) {
	    item.setOwnerId("7854265874895");
	    item.setDeclarationDate(new Date());
	    itemManager.addItem(item);
	}
	
	builder = Response.status(Response.Status.ACCEPTED).entity("Cunsumer request to add new record was approved");;
	
	return builder.build();
    }
    
    @PUT
    @Path("updateItem")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateItem(Item item) {
	System.out.println("Overwrite: " + item.toString());
	String result = item.toString();
	System.out.println(result);
	itemManager.updateItem(item);
	return Response.ok(result).build();
    }
    
    @GET
    @Path("getItem/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getItem(@PathParam("id")
    String id) {
	
	return itemManager.getItem(id);
    }
    
    @GET
    @Path("getAsset/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemBySerial(@PathParam("id")
    String id) {
	
	Response.ResponseBuilder builder = null;
	
	log.info("getOwner");
	builder = Response.ok(itemManager.getAsset(id));
	
	return builder.build();
    }
    
    @DELETE
    @Path("removeItem/{id:\\d+}")
    @Consumes("*/*")
    public void removeItem(@PathParam("id")
    String id) {
	log.info("removeItem");
	System.out.println(id);
	itemManager.removeItem(id);
    }
}

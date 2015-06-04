package za.co.authoritativelabpro.rest;

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

import za.co.authoritativelabpro.api.ContactManager;
import za.co.authoritativelabpro.api.ItemManager;
import za.co.authoritativelabpro.api.OwnerManager;
import za.co.authoritativelabpro.model.Item;
import za.co.authoritativelabpro.model.Owner;

@Path("/")
@RequestScoped
public class ItemRestService {
	
	@Inject
	ItemManager itemManager;
	
	private static final Logger log = Logger.getLogger(ItemRestService.class);
	
	@Path("getItems")
	@GET
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<Item> getItem(){
		log.info("getItems");;
		return itemManager.getItems();
	}
	
	
	@POST
	@Path("createItem")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createItem(Item item){
		String result = item.toString();
		itemManager.addItem(item);
		return Response.ok(result).build();
	}
	
	@PUT
	@Path("updateItem")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateItem(Item item){
		System.out.println("Overwrite: "+item.toString());
		String result = item.toString();
		System.out.println(result);
		itemManager.updateItem(item);
		return Response.ok(result).build();
	}
	
	@GET 
	@Path("getItem/{id:\\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Item getItem(@PathParam("id") String id){
		
		return itemManager.getItem(id);
	}
	
	//@DELETE
	@Path("removeItem/{id:\\d+}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void removeItem(@PathParam("id") String id){
		log.info("removeItem");
		System.out.println(id);
		itemManager.removeItem(id);
	}
}

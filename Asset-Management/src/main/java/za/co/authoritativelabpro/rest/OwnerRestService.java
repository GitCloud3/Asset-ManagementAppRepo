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

import za.co.authoritativelabpro.api.OwnerManager;
import za.co.authoritativelabpro.model.Owner;

@Path("/")
@RequestScoped
public class OwnerRestService {

	@Inject
	OwnerManager ownerManager;
	
	private static final Logger log = Logger.getLogger(OwnerRestService.class);
	
	
	@Path("getOwners")
	@GET
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<Owner> getOwners(){
		log.info("getOwners");;
		return ownerManager.getOwners();
	}
	
	
	@POST
	@Path("createOwner")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createOwner(Owner owner){
		String result = owner.toString();
		ownerManager.addOwner(owner);
		return Response.ok(result).build();
	}
	
	@PUT
	@Path("updateOwner")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Owner owner){
		System.out.println("Overwrite: "+owner.toString());
		String result = owner.toString();
		System.out.println(result);
		ownerManager.updateOwner(owner);
		return Response.ok(result).build();
	}
	
	@GET 
	@Path("getOwner/{id:\\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Owner getOwner(@PathParam("id") String id){
		
		return ownerManager.getOwner(id);
	}
	
	//@DELETE
	@Path("removeOwner/{id:\\d+}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void removeOwner(@PathParam("id") String id){
		log.info("remove");
		System.out.println(id);
		ownerManager.removeOwner(id);
	}
	
}

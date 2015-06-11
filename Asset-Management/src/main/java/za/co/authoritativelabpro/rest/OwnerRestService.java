package za.co.authoritativelabpro.rest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;

import za.co.authoritativelabpro.api.OwnerManager;
import za.co.authoritativelabpro.model.Owner;
import za.co.authoritativelabpro.secure.UrlSigner;

@Path("/")
@RequestScoped
public class OwnerRestService {

	@Inject
	OwnerManager ownerManager;
	
	@Context
	private UriInfo uriInfo;
	
	UrlSigner signer = new UrlSigner();
	
	private static final Logger log = Logger.getLogger(OwnerRestService.class);
	
	private static final String UNISA_SHARED_KEY = "25125154dsad4da25=";

	
	
	@Path("getOwners")
	@GET
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOwners(@QueryParam("clientId") String clientId, @QueryParam("signature") String signature) throws IOException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException{	
		
		Response.ResponseBuilder builder = null;
		
		System.out.println("Client generated key:"+signature);
		
		String resourceUrl = uriInfo.getAbsolutePath().toString();
		
		String sign = signer.calculate(resourceUrl, clientId,UNISA_SHARED_KEY);
		
		System.out.println("Server generated key:"+sign);
		
		if(sign.equals(signature)){
			log.info("getOwners");
			builder = Response.ok(ownerManager.getOwners());
		}
		else{	
			builder = Response.status(Response.Status.UNAUTHORIZED).entity("Cunsumer not Authorized");
		}
		
		return builder.build();
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

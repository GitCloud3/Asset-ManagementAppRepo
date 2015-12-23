package za.co.authoritativelabpro.rest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
    OwnerManager		ownerManager;
    
    @Context
    private UriInfo	     uriInfo;
    
    private UrlSigner	   signer	  = new UrlSigner();
    
    private static final Logger log	     = Logger.getLogger(OwnerRestService.class);
    
    private static final String HMAC_SHARED_KEY = "25125154dsad4da25=";
    
    @Path("getOwners")
    @GET
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwners(@QueryParam("clientId")
    String clientId, @QueryParam("signature")
    String signature) throws IOException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {
	
	Response.ResponseBuilder builder = null;
	
	System.out.println("Client generated key:" + signature);
	
	String resourceUrl = uriInfo.getAbsolutePath().toString();
	
	String sign = signer.calculate(resourceUrl, clientId, HMAC_SHARED_KEY);
	
	System.out.println("Server generated key:" + sign);
	
	if (sign.equals(signature)) {
	    log.info("getOwners");
	    builder = Response.ok(ownerManager.getOwners());
	}
	else {
	    builder = Response.status(Response.Status.UNAUTHORIZED).entity("Cunsumer not authorized to get read owners");
	}
	
	return builder.build();
    }
    
    @POST
    @Path("createOwner")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOwner(Owner owner) {
	Response.ResponseBuilder builder = null;
	
	System.out.println(owner.toString());
	
	ownerManager.addOwner(owner);
	
	builder = Response.status(Response.Status.ACCEPTED).entity("Cunsumer request to add new record was approved");;
	
	return builder.build();
    }
    
    @PUT
    @Path("updateOwner")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Owner owner) {
	
	Response.ResponseBuilder builder = null;
	
	log.info("getOwner");
	builder = Response.ok(ownerManager.updateOwner(owner));
	
	return builder.build();
    }
    
    @GET
    @Path("getOwner/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwner(@PathParam("id")
    String id) {
	
	Response.ResponseBuilder builder = null;
	
	log.info("getOwner");
	builder = Response.ok(ownerManager.getOwner(id));
	
	return builder.build();
    }
    
    @GET
    @Path("getOwnerRecord/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwnerRecor(@PathParam("id")
    String id) {
	
	Response.ResponseBuilder builder = null;
	
	log.info("getOwner");
	builder = Response.ok(ownerManager.getOwnerRecord(id));
	
	return builder.build();
    }
    
    @DELETE
    @Path("removeOwner/{id:\\d+}")
    @Consumes("*/*")
    public Response removeOwner(@PathParam("id")
    String id) {
	
	log.info("remove");
	System.out.println(id);
	ownerManager.removeOwner(id);
	
	return Response.noContent().build();
    }
    
}

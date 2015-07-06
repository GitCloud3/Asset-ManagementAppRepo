package za.co.authoritativelabpro.rest;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import za.co.authoritativelabpro.api.ContactManager;
import za.co.authoritativelabpro.api.ContactManager;
import za.co.authoritativelabpro.api.ContactManager;
import za.co.authoritativelabpro.converter.ContactParser;
import za.co.authoritativelabpro.model.Contact;
import za.co.authoritativelabpro.model.Contact;
import za.co.authoritativelabpro.model.Owner;
import za.co.authoritativelabpro.secure.UrlSigner;

@Path("/")
@RequestScoped
public class ContactRestService {

	@Inject
	ContactManager contactManager;
	
	@Context
	private UriInfo uriInfo;
	
	private UrlSigner signer = new UrlSigner();
	private static final String HMAC_SHARED_KEY = "25125154dsad4da25=";
	private static final Logger log = Logger.getLogger(ContactRestService.class);
	
	
	@Path("getContacts")
	@GET
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContacts(@QueryParam("clientId") String clientId, @QueryParam("signature") String signature) throws IOException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException{
		Response.ResponseBuilder builder = null;
		
		System.out.println("Client generated key:"+signature);
		
		String resourceUrl = uriInfo.getAbsolutePath().toString();
		
		String sign = signer.calculate(resourceUrl, clientId,HMAC_SHARED_KEY);
		
		System.out.println("Server generated key:"+sign);
		
		if(sign.equals(signature)){
			log.info("getOwners");
			builder = Response.ok(contactManager.getContacts());
		}
		else{	
			builder = Response.status(Response.Status.UNAUTHORIZED).entity("Cunsumer not authorized to get read contacts");
		}
		
		return builder.build();
	}
	
	
	@POST
	@Path("createContact")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createContact(String contacts) {
		
		Response.ResponseBuilder builder = null;
		
		ContactParser cp = new ContactParser();
		
		List<Contact> contactList = cp.passser(contacts);
		
		for(Contact contact : contactList){
			contact.setOwnerId("7854265874895");
			contactManager.addContact(contact);
		}

		builder = Response.status(Response.Status.ACCEPTED).entity("Cunsumer request to add new record was approved");;
		
		return builder.build();
	}
	
	@PUT
	@Path("updateContact")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateContact(Contact contact){
		System.out.println("Overwrite: "+contact.toString());
		String result = contact.toString();
		System.out.println(result);
		contactManager.updateContact(contact);
		return Response.ok(result).build();
	}
	
	@GET 
	@Path("getContact/{id:\\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContact(@PathParam("id") String id){
		
		Response.ResponseBuilder builder = null;

		log.info("getOwners");
		builder = Response.ok(contactManager.getContact(id));

		return builder.build();
	}
	@GET 
	@Path("getContactById/{id:\\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContactByRecordId(@PathParam("id") int id, @QueryParam("clientId") String clientId, @QueryParam("signature") String signature) throws IOException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {
		
		Response.ResponseBuilder builder = null;
		
		System.out.println("Client generated key:"+signature);
		
		String resourceUrl = uriInfo.getAbsolutePath().toString();
		
		String sign = signer.calculate(resourceUrl, clientId,HMAC_SHARED_KEY);
		
		System.out.println("Server generated key:"+sign);
		
		if(sign.equals(signature)){
			log.info("getOwners");
			builder = Response.ok(contactManager.getContactByRecordID(id));
		}
		else{	
			builder = Response.status(Response.Status.UNAUTHORIZED).entity("Cunsumer not authorized to get read contacts by Id");
		}
		
		return builder.build();
	}
	
	@DELETE
	@Path("removeContact/{id:\\d+}")
	@Consumes("*/*")
	public Response removeContact(@PathParam("id") String id, @QueryParam("clientId") String clientId, @QueryParam("signature") String signature) throws IOException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {
		Response.ResponseBuilder builder = null;
		
		System.out.println("Client generated key:"+signature);
		
		String resourceUrl = uriInfo.getAbsolutePath().toString();
		
		String sign = signer.calculate(resourceUrl, clientId,HMAC_SHARED_KEY);
		
		System.out.println("Server generated key:"+sign);
		
		if(sign.equals(signature)){
			log.info("removeContact");
			builder = Response.ok(contactManager.removeContact(id));
		}
		else{	
			builder = Response.status(Response.Status.UNAUTHORIZED).entity("Cunsumer not authorized to remove a contacts");
		}
		
		return builder.build();
	}
}

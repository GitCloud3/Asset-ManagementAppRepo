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
import za.co.authoritativelabpro.api.ContactManager;
import za.co.authoritativelabpro.api.ContactManager;
import za.co.authoritativelabpro.model.Contact;
import za.co.authoritativelabpro.model.Contact;

@Path("/")
@RequestScoped
public class ContactRestService {

	@Inject
	ContactManager contactManager;
	
	private static final Logger log = Logger.getLogger(ContactRestService.class);
	
	
	@Path("getContacts")
	@GET
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> getContact(){
		log.info("getContacts");;
		return contactManager.getContacts();
	}
	
	
	@POST
	@Path("createContact")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createContact(Contact contact){
		String result = contact.toString();
		contactManager.addContact(contact);
		return Response.ok(result).build();
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
	public Contact getContact(@PathParam("id") String id){
		
		return contactManager.getContact(id);
	}
	
	//@DELETE
	@Path("removeContact/{id:\\d+}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void removeContact(@PathParam("id") String id){
		log.info("removeContact");
		System.out.println(id);
		contactManager.removeContact(id);
	}
}

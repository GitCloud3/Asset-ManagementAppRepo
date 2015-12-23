package za.co.authoritativelabpro.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Assert;
import org.junit.Test;

import za.co.authoritativelabpro.hmac.UrlSignerClient;
import za.co.authoritativelabpro.model.Contact;
import za.co.authoritativelabpro.model.Item;
import za.co.authoritativelabpro.model.Owner;
import za.co.authoritativelabpro.secure.UrlSigner;

public class ContactClientTest {
    
    static final String	 ROOT_URL	= "http://localhost:8080/Asset-Management/declaration-ws/";
    
    private static final String HMAC_SHARED_KEY = "25125154dsad4da25=";
    
    private static final String CLIENT_ID       = "unisa";
    
    private UrlSignerClient     us	      = new UrlSignerClient();
    
    @Test
    public void createContact() throws IOException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {
	
	// Creation of contacts to added to hashmap of ontacts
	Contact contact = new Contact(11, "9309070361808", "+27722525145", "sithole@alp.com");
	
	ResteasyClient client = new ResteasyClientBuilder().build();
	ResteasyWebTarget target = client.target(us.makeRESRCall(ROOT_URL + "createContact", CLIENT_ID, HMAC_SHARED_KEY));
	Response response = target.request().post(Entity.entity(contact, MediaType.APPLICATION_JSON));
	
	System.out.println("HTTP status: " + response.getStatus());
	// Read output in string format
	String owners = response.readEntity(String.class);
	
	System.out.println(owners);
    }
    
    @Test
    public void getContacts() {
	ResteasyClient client = new ResteasyClientBuilder().build();
	ResteasyWebTarget target = client.target(ROOT_URL + "getContacts");
	
	Response response = target.request().get();
	
	System.out.println("HTTP status: " + response.getStatus());
	// Read output in string format
	String contacts = response.readEntity(String.class);
	
	System.out.println(contacts);
	
    }
    
    @Test
    public void getContact() throws IOException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {
	String contactId = "9012316127089";
	
	ResteasyClient client = new ResteasyClientBuilder().build();
	ResteasyWebTarget target = client.target(ROOT_URL + "getContact/" + contactId);
	
	Response response = target.request().get();
	
	System.out.println("HTTP status: " + response.getStatus());
	// Read output in string format
	String contacts = response.readEntity(String.class);
	
	System.out.println(contacts);
    }
    
    @Test
    public void removeContact() throws IOException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {
	String contactId = "9309070361808";
	
	ResteasyClient client = new ResteasyClientBuilder().build();
	ResteasyWebTarget target = client.target(us.makeRESRCall(ROOT_URL + "removeContact/" + contactId, CLIENT_ID, HMAC_SHARED_KEY));
	
	Response response = target.request().get();
	
	System.out.println("HTTP status: " + response.getStatus());
	// Read output in string format
	String contacts = response.readEntity(String.class);
	
	System.out.println(contacts);
    }
    
    @Test
    public void updateContact() throws IOException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {
	String ownerId = "9309070361808";
	
	// Read the owner entity to be updated first
	
	ResteasyClient client = new ResteasyClientBuilder().build();
	ResteasyWebTarget target = client.target(us.makeRESRCall(ROOT_URL + "updateContact/" + ownerId, CLIENT_ID, HMAC_SHARED_KEY));
	
	Response response = target.request().get();
	System.out.println("response: " + response.getStatus());
	
	Contact contact = response.readEntity(Contact.class);
	
	// From the fetched entity set new data to it and then send it back to be updated
	
	contact.setEmail("Mag-e@My_Life.mp");
	
	client = new ResteasyClientBuilder().build();
	target = client.target(us.makeRESRCall(ROOT_URL + "updateContact", CLIENT_ID, HMAC_SHARED_KEY));
	response = target.request().put(Entity.entity(contact, MediaType.APPLICATION_JSON));
	
	System.out.println("HTTP status:" + response.getStatus());
	// Read output in string format
	String contacts = response.readEntity(String.class);
	
	System.out.println(contacts);
    }
    
}

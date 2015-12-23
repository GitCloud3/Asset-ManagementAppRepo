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

public class OwnerClientTest {
    
    UrlSignerClient	     us	      = new UrlSignerClient();
    
    private static final String HMAC_SHARED_KEY = "25125154dsad4da25=";
    
    private static final String CLIENT_ID       = "unisa";
    
    static final String	 ROOT_URL	= "http://localhost:8080/Asset-Management/declaration-ws/";
    
    @Test
    public void createOwner() throws IOException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {
	
	// Creation of Contacts hashmap to hold contacts
	Set<Contact> contacts = new HashSet<Contact>();
	
	// Creation of contacts to added to hashmap of ontacts
	Contact contact = new Contact(7, "8605169582089", "+277963254821", "lovemore@thuli.com");
	Contact contact2 = new Contact(8, "8605169582089", "+27791527411", "lovemore@sbu.com");
	contacts.add(contact);
	contacts.add(contact2);
	
	// Creation of Items hashmap to hold items
	Set<Item> items = new HashSet<Item>();
	
	// Creation of items to added to hashmap of Items
	Item item = new Item("ERWRKWER145244", "8605169582089", "Computer", "Lenovo", "LN2552RT", "Lenovo Labs", "Black", "7708062158409", new Date());
	items.add(item);
	
	// Adding owner data
	Owner owner = new Owner("8605169582089", "Mr", "Lovemore", "Mabona", "Male", "African", "South Africa", "Gauteng", "MP", contacts, items);
	
	ResteasyClient client = new ResteasyClientBuilder().build();
	ResteasyWebTarget target = client.target(us.makeRESRCall(ROOT_URL + "createOwner", CLIENT_ID, HMAC_SHARED_KEY));
	Response response = target.request().post(Entity.entity(owner, MediaType.APPLICATION_JSON));
	
	System.out.println("HTTP status" + response.getStatus());
	// Read output in string format
	String owners = response.readEntity(String.class);
	
	System.out.println(owners);
    }
    
    @Test
    public void getOwners() throws IOException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {
	
	ResteasyClient client = new ResteasyClientBuilder().build();
	
	ResteasyWebTarget target = client.target(us.makeRESRCall(ROOT_URL + "getOwners", CLIENT_ID, HMAC_SHARED_KEY));
	
	Response response = target.request().get();
	
	System.out.println("HTTP status: " + response.getStatus());
	// Read output in string format
	String owners = response.readEntity(String.class);
	
	System.out.println(owners);
	
    }
    
    @Test
    public void getOwner() throws IOException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {
	String ownerId = "9012316127089";
	
	ResteasyClient client = new ResteasyClientBuilder().build();
	ResteasyWebTarget target = client.target(ROOT_URL + "getOwner/" + ownerId);
	
	Response response = target.request().get();
	
	System.out.println("HTTP status" + response.getStatus());
	// Read output in string format
	String owners = response.readEntity(String.class);
	
	System.out.println(owners);
	
    }
    
    @Test
    public void removeOwner() {
	String ownerId = "9012316127089";
	
	ResteasyClient client = new ResteasyClientBuilder().build();
	ResteasyWebTarget target = client.target(ROOT_URL + "removeOwner/" + ownerId);
	
	Response response = target.request().accept(MediaType.APPLICATION_JSON).delete();
	
	System.out.println("HTTP status: " + response.getStatus());
	// Read output in string format
    }
    
    @Test
    public void updateOwner() throws IOException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {
	String ownerId = "9309070361808";
	String recordId = "11";
	
	// Read the owner entity to be updated first
	
	ResteasyClient client = new ResteasyClientBuilder().build();
	ResteasyWebTarget target = client.target(us.makeRESRCall(ROOT_URL + "getContact/" + ownerId, CLIENT_ID, HMAC_SHARED_KEY));
	
	Response response = target.request().get();
	System.out.println("response: " + response.getStatus());
	
	Owner owner = response.readEntity(Owner.class);
	
	System.out.println("Owner:" + owner.toString());
	
	// From the fetched entity set new data to it and then send it back to be updated
	
	owner.setName("Mag-e My_Life!");
	
	client = new ResteasyClientBuilder().build();
	target = client.target(us.makeRESRCall(ROOT_URL + "updateOwner", CLIENT_ID, HMAC_SHARED_KEY));
	response = target.request().put(Entity.entity(owner, MediaType.APPLICATION_JSON));
	
	System.out.println("HTTP status" + response.getStatus());
	// Read output in string format
	String owners = response.readEntity(String.class);
	
	System.out.println(owners);
    }
    
}

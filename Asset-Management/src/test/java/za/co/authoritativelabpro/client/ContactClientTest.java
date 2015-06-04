package za.co.authoritativelabpro.client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Assert;
import org.junit.Test;

import za.co.authoritativelabpro.model.Contact;
import za.co.authoritativelabpro.model.Item;
import za.co.authoritativelabpro.model.Owner;

public class ContactClientTest {
	static final String ROOT_URL = "http://localhost:8080/Asset-Management/declaration-ws/";

	@Test
	public void getItems() {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(ROOT_URL + "getContacts");

		Response response = target.request().get();

		System.out.println("HTTP status" + response.getStatus());
		// Read output in string format
		String contacts = response.readEntity(String.class);

		System.out.println(contacts);

	}

	@Test
	public void getIContact() {
		int contactId = 1;

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client
				.target(ROOT_URL + "getContact/" + contactId);

		Response response = target.request().get();

		System.out.println("HTTP status" + response.getStatus());
		// Read output in string format
		String contact = response.readEntity(String.class);

		System.out.println(contact);

	}

	@Test
	public void removeContact() {
		int contactId = 1;

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(ROOT_URL + "removeContact/" + contactId);

		Response response = target.request().accept(MediaType.APPLICATION_JSON)
				.delete();

		System.out.println("HTTP status: " + response.getStatus());
		// Read output in string format
	}

	@Test
	public void updateItem() {
		int contactId = 1;
		
		//Read the Item entity to be updated first
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(ROOT_URL + "updateContact/" + contactId);

		Response response = target.request().get();
		System.out.println("response:"+response.getStatus());
		
		Contact contact = response.readEntity(Contact.class);
		System.out.println("Item:"+contact);
		//From the fetched entity set new data to it and then send it back to be updated
		
		contact.setEmail("sibonisor@live.com");
		client = new ResteasyClientBuilder().build();
		target = client.target(ROOT_URL + "update");
		response = target.request().put(
				Entity.entity(contact, MediaType.APPLICATION_JSON));

		Assert.assertEquals(response.getStatus(), 200);
	}

}

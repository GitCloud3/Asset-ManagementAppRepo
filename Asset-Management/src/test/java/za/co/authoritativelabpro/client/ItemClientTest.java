package za.co.authoritativelabpro.client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Assert;
import org.junit.Test;

import za.co.authoritativelabpro.model.Item;
import za.co.authoritativelabpro.model.Owner;

public class ItemClientTest {
	static final String ROOT_URL = "http://localhost:8080/Asset-Management/declaration-ws/";

	@Test
	public void getItems() {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(ROOT_URL + "getItems");

		Response response = target.request().get();

		System.out.println("HTTP status" + response.getStatus());
		// Read output in string format
		String items = response.readEntity(String.class);

		System.out.println(items);

	}

	@Test
	public void getItem() {
		String serialNumber = "254859636555245";

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client
				.target(ROOT_URL + "getItem/" + serialNumber);

		Response response = target.request().get();

		System.out.println("HTTP status" + response.getStatus());
		// Read output in string format
		String items = response.readEntity(String.class);

		System.out.println(items);

	}

	@Test
	public void removeItem() {
		String serialNumber = "254859636555245";

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(ROOT_URL + "removeItem/" + serialNumber);

		Response response = target.request().accept(MediaType.APPLICATION_JSON)
				.delete();

		System.out.println("HTTP status: " + response.getStatus());
		// Read output in string format
	}

	@Test
	public void updateItem() {
		String serialNumber = "254859636555245";
		
		//Read the Item entity to be updated first
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(ROOT_URL + "updateItem/" + serialNumber);

		Response response = target.request().get();
		System.out.println("response:"+response.getStatus());
		
		Item item = response.readEntity(Item.class);
		System.out.println("Item:"+item);
		//From the fetched entity set new data to it and then send it back to be updated
		
		item.setName("HTC");
		client = new ResteasyClientBuilder().build();
		target = client.target(ROOT_URL + "update");
		response = target.request().put(
				Entity.entity(item, MediaType.APPLICATION_JSON));

		Assert.assertEquals(response.getStatus(), 200);
	}

}

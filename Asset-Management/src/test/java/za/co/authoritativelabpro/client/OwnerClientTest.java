package za.co.authoritativelabpro.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Assert;
import org.junit.Test;

import za.co.authoritativelabpro.hmac.UrlSignerClient;
import za.co.authoritativelabpro.model.Item;
import za.co.authoritativelabpro.model.Owner;

public class OwnerClientTest {
	UrlSignerClient us = new UrlSignerClient();
	
	static final String ROOT_URL = "http://localhost:8080/Asset-Management/declaration-ws/";

	@Test
	public void getOwners() throws IOException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException{
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = client.target(us.makeRESRCall(ROOT_URL + "getOwners", "unisa", "35125154dsad4da25="));

		Response response = target.request().get();

		System.out.println("HTTP status: " + response.getStatus());
		// Read output in string format
		String owners = response.readEntity(String.class);

		System.out.println(owners);

	}

	@Test
	public void getOwner() {
		String ownerId = "9012316127089";

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client
				.target(ROOT_URL + "getOwner/" + ownerId);

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

		Response response = target.request().accept(MediaType.APPLICATION_JSON)
				.delete();

		System.out.println("HTTP status: " + response.getStatus());
		// Read output in string format
	}

	@Test
	public void updateOwner() {
		String ownerId = "9012316127089";
		
		//Read the owner entity to be updated first
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(ROOT_URL + "updateOwner/" + ownerId);

		Response response = target.request().get();
		System.out.println("response:"+response.getStatus());
		
		Owner owner = response.readEntity(Owner.class);
		System.out.println("Owner:"+owner);
		//From the fetched entity set new data to it and then send it back to be updated
		
		owner.setName("Siboniso Ronald Pro");
		client = new ResteasyClientBuilder().build();
		target = client.target(ROOT_URL + "update");
		response = target.request().put(
				Entity.entity(owner, MediaType.APPLICATION_JSON));

		Assert.assertEquals(response.getStatus(), 200);
	}

}

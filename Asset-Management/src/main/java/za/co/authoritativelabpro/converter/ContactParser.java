package za.co.authoritativelabpro.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.svenson.JSONParser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import za.co.authoritativelabpro.model.Contact;

public class ContactParser {
	
	public List<Contact> passser(final String json){
		
		List<Contact> contacts = new ArrayList<Contact>();
		
		JSONParser parser = new JSONParser();
		
		parser.addTypeHint("contacts[]", Contact.class);
		
		@SuppressWarnings("unchecked")
		Map<String, List<Contact>> results1 = parser.parse(Map.class, json);
		
		for(Entry<String, List<Contact>> entry : results1.entrySet()){

			TypeToken <List<Contact>> tocken =  new TypeToken<List<Contact>>(){};
			contacts = new Gson().fromJson(entry.getValue().toString(), tocken.getType());
		}
		return contacts;
	}
}

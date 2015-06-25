/*
 * tsithosr
 */

package za.co.authoritativelabpro.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.svenson.JSONParser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import za.co.authoritativelabpro.model.Item;

public class ItemParser {
	
	public List<Item> passser(final String json){
		
		List<Item> Items = new ArrayList<Item>();
		
		JSONParser parser = new JSONParser();
		
		parser.addTypeHint("Items[]", Item.class);
		
		@SuppressWarnings("unchecked")
		Map<String, List<Item>> results1 = parser.parse(Map.class, json);
		
		for(Entry<String, List<Item>> entry : results1.entrySet()){
			System.out.println("::: "+entry.getValue());
			
			TypeToken <List<Item>> tocken =  new TypeToken<List<Item>>(){};
			Items = new Gson().fromJson(entry.getValue().toString(), tocken.getType());
		}
		return Items;
	}
}

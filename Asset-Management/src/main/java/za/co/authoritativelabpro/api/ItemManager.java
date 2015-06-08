package za.co.authoritativelabpro.api;

import java.util.List;

import javax.ejb.Local;

import za.co.authoritativelabpro.model.Item;

@Local
public interface ItemManager {
	
	/**
	 * Dealing with the child entity - Item
	 *
	 */
	public String removeItem(String id);
	
	public Item addItem(Item item);
	
	public List<Item> getItem(String id);
	
	public List<Item> getItems();
	
	public String updateItem(Item item);
	
}

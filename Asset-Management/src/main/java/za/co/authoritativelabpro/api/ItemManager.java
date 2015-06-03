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
	public String removeItem(int id);
	
	public Item addItem(Item item);
	
	public Item getItem(int id);
	
	public List<Item> getItems();
	
	public String updateItem(Item item);
	
}

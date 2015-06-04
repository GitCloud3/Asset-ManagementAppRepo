package za.co.authoritativelabpro.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import za.co.authoritativelabpro.api.ItemManager;
import za.co.authoritativelabpro.model.Item;

@Stateless
public class ItemBean implements ItemManager {
	
	@PersistenceContext(name="declarationPU")
	private EntityManager em;
	private static final Logger log = Logger.getLogger(ItemBean.class);
	
	public String removeItem(String id) {
		// TODO Auto-generated method stub
		log.info("em: removeItem");
		Item item = em.find(Item.class, id);
		em.remove(item);
		return "Item removed";
	}

	public Item addItem(Item item) {
		// TODO Auto-generated method stub
		log.info("em: addItem");
		em.persist(item);
		return item;
	}

	public Item getItem(String id) {
		// TODO Auto-generated method stub
		log.info("em: getItem");
		Item item = em.find(Item.class, id);
		return item;
	}

	public List<Item> getItems() {
		// TODO Auto-generated method stub
		List<Item> item = em.createQuery("SELECT o FROM Item o", Item.class).getResultList();
		return item;
	}

	public String updateItem(Item item) {
		// TODO Auto-generated method stub
		log.info("em: updateItem");
		em.merge(item);
		return "Item updated!";
	}

}

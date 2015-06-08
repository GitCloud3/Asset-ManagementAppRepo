package za.co.authoritativelabpro.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import za.co.authoritativelabpro.api.ItemManager;
import za.co.authoritativelabpro.model.Contact;
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

	@SuppressWarnings("unchecked")
	public List<Item> getItem(String ownerId) {
		// TODO Auto-generated method stub
		log.info("em: getItem");
		Query query = em.createQuery("SELECT i FROM Item i WHERE i.ownerId = ?1");
		query.setParameter(1, ownerId);
		return new ArrayList<Item>(query.getResultList());
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

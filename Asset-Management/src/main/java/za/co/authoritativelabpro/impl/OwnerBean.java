package za.co.authoritativelabpro.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import za.co.authoritativelabpro.api.OwnerManager;
import za.co.authoritativelabpro.model.Contact;
import za.co.authoritativelabpro.model.Item;
import za.co.authoritativelabpro.model.Owner;

@Stateless
public class OwnerBean implements OwnerManager {
    
    @PersistenceContext(name = "declarationPU")
    private EntityManager       em;
    
    private static final Logger log = Logger.getLogger(OwnerBean.class);
    
    public String removeOwner(String id) {
	// TODO Auto-generated method stub
	log.info("em: removeOwner");
	Owner owner = em.find(Owner.class, id);
	
	for (Contact contact : owner.getContacts()) {
	    em.remove(contact);
	}
	for (Item item : owner.getItems()) {
	    em.remove(item);
	}
	owner.setContacts(null);
	owner.setItems(null);
	
	em.remove(owner);
	
	return "Owner removed";
    }
    
    public Owner addOwner(Owner owner) {
	// TODO Auto-generated method stub
	log.info("em: addOwner");
	em.persist(owner);
	return owner;
    }
    
    public List<Owner> getOwner(String id) {
	// TODO Auto-generated method stub
	log.info("em: getOwner");
	ArrayList<Owner> owners = new ArrayList<Owner>();
	Owner owner = em.find(Owner.class, id);
	owners.add(owner);
	return owners;
    }
    
    public List<Owner> getOwners() {
	// TODO Auto-generated method stub
	List<Owner> owner = em.createQuery("SELECT o FROM Owner o", Owner.class).getResultList();
	return owner;
    }
    
    public String updateOwner(Owner owner) {
	// TODO Auto-generated method stub
	log.info("em: updateOwner");
	em.merge(owner);
	return "Owner updated!";
    }
    
    public Owner getOwnerRecord(String id) {
	// TODO Auto-generated method stub
	log.info("em: getOwnerRecord");
	Owner owner = em.find(Owner.class, id);
	return owner;
    }
    
}

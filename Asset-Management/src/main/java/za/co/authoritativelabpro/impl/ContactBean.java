package za.co.authoritativelabpro.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import za.co.authoritativelabpro.api.ContactManager;
import za.co.authoritativelabpro.model.Contact;

@Stateless
public class ContactBean implements ContactManager {
	
	@PersistenceContext(name="declarationPU")
	private EntityManager em;
	private static final Logger log = Logger.getLogger(ContactBean.class);
	
	public String removeContact(String id) {
		// TODO Auto-generated method stub
		log.info("em: removeContact");
		Contact contact = em.find(Contact.class, id);
		em.remove(contact);
		return "Contact removed";
	}

	public Contact addContact(Contact contact) {
		// TODO Auto-generated method stub
		log.info("em: addContact");
		em.persist(contact);
		return contact;
	}

	@SuppressWarnings("unchecked")
	public List<Contact> getContact(String ownerId) {
		// TODO Auto-generated method stub
		log.info("em: getContact");
		Query query = em.createQuery("SELECT c FROM Contact c WHERE c.ownerId = ?1");
		query.setParameter(1, ownerId);
		return new ArrayList<Contact>(query.getResultList());
	}

	public List<Contact> getContacts() {
		// TODO Auto-generated method stub
		List<Contact> contact = em.createQuery("SELECT o FROM Contact o", Contact.class).getResultList();
		return contact;
	}

	public String updateContact(Contact contact) {
		// TODO Auto-generated method stub
		log.info("em: updateContact");
		em.merge(contact);
		return "Contact updated!";
	}

}

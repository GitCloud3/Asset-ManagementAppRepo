package za.co.authoritativelabpro.api;

import java.util.List;

import javax.ejb.Local;

import za.co.authoritativelabpro.model.Contact;

@Local
public interface ContactManager {
	
	/**
	 * Dealing with the child entity - Contact
	 *
	 */
	public String removeContact(int id);
	
	public Contact addContact(Contact item);
	
	public Contact getContact(int id);
	
	public List<Contact> getContacts();
	
	public String updateContact(Contact contact);
}

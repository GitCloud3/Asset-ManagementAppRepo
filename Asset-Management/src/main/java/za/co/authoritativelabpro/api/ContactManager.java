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
    public String removeContact(String id);
    
    public String addContact(Contact contacts);
    
    public List<Contact> getContact(String ownerId);
    
    public List<Contact> getContacts();
    
    public Contact getContactByRecordID(int id);
    
    public String updateContact(Contact contact);
}

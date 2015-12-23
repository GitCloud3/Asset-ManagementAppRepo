package za.co.authoritativelabpro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Contact")
@NamedQuery(name = "Contacts.findAll", query = "SELECT c FROM Contact c")
public class Contact {
    
    @Id
    @Column(name = "contactId")
    private int    contactId;
    
    @Column(name = "ownerId")
    private String ownerId;
    
    @Column(name = "telephone")
    private String telephone;
    
    @Column(name = "email")
    private String email;
    
    public Contact() {
	super();
    }
    
    public Contact(int contactId, String ownerId, String telephone, String email) {
	super();
	this.contactId = contactId;
	this.ownerId = ownerId;
	this.telephone = telephone;
	this.email = email;
    }
    
    public int getContactId() {
	return contactId;
    }
    
    public void setContactId(int contactId) {
	this.contactId = contactId;
    }
    
    public String getOwnerId() {
	return ownerId;
    }
    
    public void setOwnerId(String ownerId) {
	this.ownerId = ownerId;
    }
    
    public String getTelephone() {
	return telephone;
    }
    
    public void setTelephone(String telephone) {
	this.telephone = telephone;
    }
    
    public String getEmail() {
	return email;
    }
    
    public void setEmail(String email) {
	this.email = email;
    }
    
    @Override
    public String toString() {
	return "Contact [contactId=" + contactId + ", ownerId=" + ownerId + ", telephone=" + telephone + ", email=" + email + "]";
    }
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + contactId;
	return result;
    }
    
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Contact other = (Contact) obj;
	if (contactId != other.contactId)
	    return false;
	return true;
    }
    
}

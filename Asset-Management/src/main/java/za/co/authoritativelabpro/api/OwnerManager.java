package za.co.authoritativelabpro.api;

import java.util.List;

import javax.ejb.Local;

import za.co.authoritativelabpro.model.Owner;

@Local
public interface OwnerManager {
	
	/**
	 * Dealing with the master entity - Owner
	 *
	 */
	public String removeOwner(String id);
	
	public Owner addOwner(Owner owner);
	
	public List<Owner> getOwner(String id);
	
	public List<Owner> getOwners();
	
	public String updateOwner(Owner owner);
	
}

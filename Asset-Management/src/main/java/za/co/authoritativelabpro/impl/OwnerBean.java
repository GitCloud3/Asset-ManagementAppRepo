package za.co.authoritativelabpro.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import za.co.authoritativelabpro.api.OwnerManager;
import za.co.authoritativelabpro.model.Owner;

@Stateless
public class OwnerBean implements OwnerManager {
	
	@PersistenceContext(name="declarationPU")
	private EntityManager em;
	private static final Logger log = Logger.getLogger(OwnerBean.class);
	
	public String removeOwner(String id) {
		// TODO Auto-generated method stub
		log.info("em: removeOwner");
		Owner owner = em.find(Owner.class, id);
		System.out.println("Override: "+owner.toString());
		em.remove(owner);
		return "Owner removed";
	}

	public Owner addOwner(Owner owner) {
		// TODO Auto-generated method stub
		log.info("em: addOwner");
		em.persist(owner);
		return owner;
	}

	public Owner getOwner(String id) {
		// TODO Auto-generated method stub
		log.info("em: getOwner");
		Owner owner = em.find(Owner.class, id);
		return owner;
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

}

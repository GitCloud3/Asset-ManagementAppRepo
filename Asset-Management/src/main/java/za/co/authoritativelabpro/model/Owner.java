package za.co.authoritativelabpro.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

@Entity
@Table(name="Owner")
@NamedQuery(name="Owner.findAll", query="SELECT o FROM Owner o")
public class Owner {
	
	@Id
	@Column(name="ownerId")
	private String ownerId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="race")
	private String race;
	
	@Column(name="country")
	private String country;
	
	@Column(name="province")
	private String province;
	
	@Column(name="city")
	private String city;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name="ownerId", referencedColumnName="ownerId")
	private Set<Contact> contacts = new HashSet<Contact>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name="ownerId", referencedColumnName="ownerId")
	private Set<Item> items = new HashSet<Item>();
	
	
	public Owner() {
		super();
	}
	
	public Owner(String ownerId, String title, String name, String surname,
			String gender, String race, String country, String province,
			String city, Set<Contact> contacts, Set<Item> items) {
		super();
		this.ownerId = ownerId;
		this.title = title;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.race = race;
		this.country = country;
		this.province = province;
		this.city = city;
		this.contacts = contacts;
		this.items = items;
	}



	public String getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getRace() {
		return race;
	}
	
	public void setRace(String race) {
		this.race = race;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Owner [ownerId=" + ownerId + ", title=" + title + ", name="
				+ name + ", surname=" + surname + ", gender=" + gender
				+ ", race=" + race + ", country=" + country + ", province="
				+ province + ", city=" + city + ", contacts=" + contacts
				+ ", items=" + items + "]";
	}
	
}

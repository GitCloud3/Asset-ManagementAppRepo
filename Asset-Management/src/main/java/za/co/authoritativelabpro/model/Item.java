package za.co.authoritativelabpro.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

@Entity
@Table(name="Item")
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item {
	
	@Id
	@Column(name="serialnumber")
	private String serialnumber;
	
	@Column(name="ownerId")
	private String ownerId;
	
	@Column(name="type")
	private String type;
	
	@Column(name="name")
	private String name;
	
	@Column(name="modelnumber")
	private String modelnumber;
	
	@Column(name="manufacture")
	private String manufacture;
	
	@Column(name="colour")
	private String color;
	
	@Column(name="declarerId")
	private String declarerId;
	
	@Column(name="date")
	private Date declarationDate;
	
	
	public Item() {
		super();
	}
	public Item(String serialnumber, String ownerId, String type, String name,
			String modelnumber, String manufacture, String color,
			String declarerId, Date declarationDate) {
		super();
		this.serialnumber = serialnumber;
		this.ownerId = ownerId;
		this.type = type;
		this.name = name;
		this.modelnumber = modelnumber;
		this.manufacture = manufacture;
		this.color = color;
		this.declarerId = declarerId;
		this.declarationDate = declarationDate;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModelnumber() {
		return modelnumber;
	}
	public void setModelnumber(String modelnumber) {
		this.modelnumber = modelnumber;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDeclarerId() {
		return declarerId;
	}
	public void setDeclarerId(String declarerId) {
		this.declarerId = declarerId;
	}
	public Date getDeclarationDate() {
		return declarationDate;
	}
	public void setDeclarationDate(Date declarationDate) {
		this.declarationDate = declarationDate;
	}
	@Override
	public String toString() {
		return "Item [serialnumber=" + serialnumber + ", ownerId=" + ownerId
				+ ", type=" + type + ", name=" + name + ", modelnumber="
				+ modelnumber + ", manufacture=" + manufacture + ", color="
				+ color + ", declarerId=" + declarerId + ", declarationDate="
				+ declarationDate + "]";
	}
}

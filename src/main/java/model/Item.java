package model;

import java.util.Date;

public class Item {
	private String name;
	private String type;
	private int price;
	private Date dateAcquired;
	private int id;
	
	public Item() {
		
	}
	public Item(String name, String type, int price, Date date) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.dateAcquired = date;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getDateAcquired() {
		return dateAcquired;
	}
	public void setDateAcquired(Date dateAcquired) {
		this.dateAcquired = dateAcquired;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return this.getId() + " | " 
				+ this.getName() + " | " 
				+ this.getType() + " | " 
				+ this.getPrice() + " | " 
				+ this.getDateAcquired().toString(); 
	}
	
	
}

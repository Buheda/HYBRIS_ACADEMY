package core.db.entity;

import java.sql.ResultSet;
import java.sql.Timestamp;

import core.util.DateTimeFormatter;

public class Products {

	private int id;

	private String name;

	private int price;

	private Products_status status;

	private Timestamp created_at;

	public Products(String name, int price, Products_status status, Timestamp created_at) {
		this.id = 0;
		this.name = name;
		this.price = price;
		this.status = status;
		this.created_at = created_at;
	}
	
	public Products(int id, String name, int price, Products_status status, Timestamp created_at) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.status = status;
		this.created_at = created_at;
	}

	public Products() {
		this(0, "", 0, Products_status.out_of_stock, DateTimeFormatter.getNow());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Products_status getStatus() {
		return status;
	}

	public void setStatus(Products_status status) {
		this.status = status;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "| "+id+" | " + name + " | " + price + " | " + status + " |";
	}
	
	public String toString(int nameFieldLength) throws Exception {
		String paddedName = name;
		if (nameFieldLength>0) 
			paddedName = String.format("%-"+nameFieldLength+"."+ nameFieldLength+"s", paddedName);
		
		return "| "+String.format("%-3.3s", id)+
				" | " + paddedName + 
				" | " + String.format("%-3.3s", price) + 
				" | " + String.format("%-12s", status) + " |";
	}
	
	//not good
	public static String showResultSet(ResultSet rs, int nameFieldLength) throws Exception {
		String paddedName = rs.getString("name");
		if (nameFieldLength>0) 
			paddedName = String.format("%-"+nameFieldLength+"."+ nameFieldLength+"s", paddedName);
		
		return "| "+String.format("%-3.3s", rs.getInt("id"))+
				" | " + paddedName + 
				" | " + String.format("%-3.3s", rs.getInt("price")) + 
				" | " + String.format("%-12s", Products_status.fromInteger(rs.getInt("status"))) + " |";
	}
	
}

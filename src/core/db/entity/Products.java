package core.db.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Products {
	
	private static final List<String> PRODUCT_STATUS_TYPES = 
			new ArrayList<String>(Arrays.asList("out_of_stock","in_stock","running_low"));

	private int id;

	private String name;

	private int price;

	private String status;

	private Timestamp created_at;

	public Products(String name, int price, String status, Timestamp created_at) {
		this.id = 0;
		this.name = name;
		this.price = price;
		this.status = status;
		this.created_at = created_at;
	}
	
	public Products(int id, String name, int price, String status, Timestamp created_at) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.status = status;
		this.created_at = created_at;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	public static List<String> getStatusTypes() {
		return PRODUCT_STATUS_TYPES;
	}	
	
	@Override
	public String toString() {
		return "| "+id+" | " + name + " | " + price + " | " + status + " |";
	}


}

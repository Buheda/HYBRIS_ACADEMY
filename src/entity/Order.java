package entity;

public class Order {

	private int id;

	private int user_id;

	private String status;

	private String created_at;

	public Order(int id, int user_id, String status, String created_at) {
		this.id = id;
		this.user_id = user_id;
		this.status = status;
		this.created_at = created_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}	
}

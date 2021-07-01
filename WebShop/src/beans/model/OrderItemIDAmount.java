package beans.model;

import java.util.UUID;

public class OrderItemIDAmount {
	private UUID id;
	private int amount;
	
	public OrderItemIDAmount() {}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}

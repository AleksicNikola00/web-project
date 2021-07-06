package beans.model;

import beans.enumerations.OrderStatus;
import java.util.*;

public class Order {
	private UUID id;
	private ArrayList<OrderItemIDAmount> orderedItems;
	private UUID restaurant;
	private java.util.Date date;
	private double price;
	private String username;
	private OrderStatus status;
	private boolean isDeleted;
	private String deliveryWorkerUsername;


	public Order() {}
	
	public String getDeliveryWorkerUsername() {
		return deliveryWorkerUsername;
	}

	public void setDeliveryWorkerUsername(String deliveryWorkerUsername) {
		this.deliveryWorkerUsername = deliveryWorkerUsername;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ArrayList<OrderItemIDAmount> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(ArrayList<OrderItemIDAmount> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public UUID getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(UUID restaurant) {
		this.restaurant = restaurant;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


}
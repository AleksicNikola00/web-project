package dto;

import java.util.ArrayList;
import java.util.UUID;

import beans.enumerations.OrderStatus;
import beans.enumerations.RestaurantType;

public class PastOrderDTO {
	private String logoPath;
	private String restaurantName;
	private ArrayList<PastOrderedItemDTO> items;
	private String date;
	private OrderStatus status;
	private RestaurantType restaurantType;
	private UUID id;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public RestaurantType getRestaurantType() {
		return restaurantType;
	}

	public void setRestaurantType(RestaurantType restaurantType) {
		this.restaurantType = restaurantType;
	}

	public PastOrderDTO() {}
	
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public ArrayList<PastOrderedItemDTO> getItems() {
		return items;
	}
	public void setItems(ArrayList<PastOrderedItemDTO> items) {
		this.items = items;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}


}
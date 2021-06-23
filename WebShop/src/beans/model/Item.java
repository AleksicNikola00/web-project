package beans.model;

import beans.enumerations.ItemType;
import beans.enumerations.ItemUnit;
import java.util.*;

public class Item {
	private UUID id;
	private String name;
	private double price;
	private ItemType type;
	private ItemUnit unitType;
	private double unitAmount;
	private String description;
	private String picturePath;
	private UUID restaurantId;
	private boolean isDeleted;

	public Item() {}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public ItemUnit getUnitType() {
		return unitType;
	}

	public void setUnitType(ItemUnit unitType) {
		this.unitType = unitType;
	}

	public double getUnitAmount() {
		return unitAmount;
	}

	public void setUnitAmount(double unitAmount) {
		this.unitAmount = unitAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public UUID getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(UUID restaurantId) {
		this.restaurantId = restaurantId;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}



}
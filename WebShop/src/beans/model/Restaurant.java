package beans.model;

import beans.enumerations.RestaurantType;
import beans.enumerations.RestaurantStatus;
import java.util.*;

public class Restaurant {
	private String name;
	private UUID id;
	private RestaurantType type;
	private RestaurantStatus status;
	private String logoPath;
	private UUID geoLocationId;
	private boolean isDeleted;
	private double rating;

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Restaurant() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public RestaurantType getType() {
		return type;
	}

	public void setType(RestaurantType type) {
		this.type = type;
	}

	public RestaurantStatus getStatus() {
		return status;
	}

	public void setStatus(RestaurantStatus status) {
		this.status = status;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public UUID getGeoLocationId() {
		return geoLocationId;
	}

	public void setGeoLocationId(UUID geoLocationId) {
		this.geoLocationId = geoLocationId;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}



}
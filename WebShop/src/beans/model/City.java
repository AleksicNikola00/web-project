package beans.model;

import java.util.*;

public class City {
	private UUID id;
	private String name;
	private String country;
	private int postalCode;
	private UUID cityId;
	private boolean isDeleted;

	public City() {}

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public UUID getCityId() {
		return cityId;
	}

	public void setCityId(UUID cityId) {
		this.cityId = cityId;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}



}
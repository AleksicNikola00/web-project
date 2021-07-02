package beans.model;

import beans.enumerations.TypeOfShopper;
import java.util.*;

public class ShopperType {
	private String typeName;
	private TypeOfShopper type;
	private double discount;
	private int requiredPoints;
	private boolean deleted;
	private String logoPath;
	private int maximumPoints;

	public int getMaximumPoints() {
		return maximumPoints;
	}

	public void setMaximumPoints(int maximumPoints) {
		this.maximumPoints = maximumPoints;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public ShopperType() {}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public TypeOfShopper getType() {
		return type;
	}

	public void setType(TypeOfShopper type) {
		this.type = type;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getRequiredPoints() {
		return requiredPoints;
	}

	public void setRequiredPoints(int requiredPoints) {
		this.requiredPoints = requiredPoints;
	}


}
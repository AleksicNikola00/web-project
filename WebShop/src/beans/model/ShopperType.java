package beans.model;

import beans.enumerations.TypeOfShopper;
import java.util.*;

public class ShopperType {
	private String typeName;
	private TypeOfShopper type;
	private double discount;
	private int requiredPoints;

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
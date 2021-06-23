package beans.model;

import beans.enumerations.TypeOfShopper;
import java.util.*;

public class Shopper extends User {
	private int collectedPoints;
	private TypeOfShopper shopperType;

	public Shopper() {}

	public int getCollectedPoints() {
		return collectedPoints;
	}

	public void setCollectedPoints(int collectedPoints) {
		this.collectedPoints = collectedPoints;
	}

	public TypeOfShopper getShopperType() {
		return shopperType;
	}

	public void setShopperType(TypeOfShopper shopperType) {
		this.shopperType = shopperType;
	}



}
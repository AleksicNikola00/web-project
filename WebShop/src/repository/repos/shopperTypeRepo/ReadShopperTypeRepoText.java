package repository.repos.shopperTypeRepo;

import java.util.ArrayList;

import beans.enumerations.TypeOfShopper;
import beans.model.ShopperType;

public class ReadShopperTypeRepoText implements IReadShopperTypeRepo {
	private static String path;

	public ReadShopperTypeRepoText(String path) {
		this.path = path;
	}

	@Override
	public ShopperType getById(TypeOfShopper id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ShopperType> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
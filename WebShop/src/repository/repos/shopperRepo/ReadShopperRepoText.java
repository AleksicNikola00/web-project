package repository.repos.shopperRepo;

import java.util.ArrayList;
import java.util.UUID;

import beans.model.Shopper;

public class ReadShopperRepoText implements IReadShopperRepo {
	private static String path;

	public ReadShopperRepoText(String path) {
		this.path = path;
	}

	@Override
	public Shopper getById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Shopper> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
package services;

import beans.model.Shopper;

public class ShopperService extends BaseService {

	public ShopperService(String path) {
		super(path);
	}
	
	public void addShopper(Shopper shopper) {
		uow.getShopperWriteRepo().add(shopper);
	}

	public Shopper getShopper(String username) {
		return uow.getShopperReadRepo().getById(username);
	}

}
package services;

import java.util.Date;
import java.util.UUID;

import beans.errors.DatabaseErrors;
import beans.model.Order;
import beans.model.Restaurant;
import beans.model.Shopper;

public class CRUDOrderService extends BaseService {
	
	public CRUDOrderService(String path) {
		super(path);
	}
	
	public String add(Order order) {
		
		Restaurant restaurantInDatabase = uow.getRestaurantReadRepo().getById(order.getRestaurant());
		Shopper shopperInDatabase = uow.getShopperReadRepo().getById(order.getUsername());
		
		if (restaurantInDatabase == null || restaurantInDatabase.isDeleted() ||
			shopperInDatabase == null || shopperInDatabase.isDeleted()){
			return DatabaseErrors.NOT_FOUND;
		}
		
		order.setId(UUID.randomUUID());
		order.setDate(new Date());
		
		uow.getOrderWriteRepo().add(order);
		
		return DatabaseErrors.NO_ERROR;
	}
}

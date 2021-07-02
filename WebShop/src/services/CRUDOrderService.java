package services;

import java.util.Date;
import java.util.UUID;

import beans.enumerations.OrderStatus;
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
	
	public String cancelOrder(UUID id) {
		Order order = uow.getOrderReadRepo().getById(id);
		
		if (order.getStatus() != OrderStatus.PENDING) {
			return DatabaseErrors.WRONG_ORDER_STATUS;
		}
		
		order.setStatus(OrderStatus.CANCELED);
		
		uow.getOrderWriteRepo().update(order);
		
		return DatabaseErrors.NO_ERROR;
	}
}

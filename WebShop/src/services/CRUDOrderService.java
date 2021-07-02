package services;

import java.util.ArrayList;
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
	
	public String submitOrders(ArrayList<Order> orders) {
		
		int succededNumber = 0;
		
		for(Order order : orders) {
			Restaurant restInDataBase = uow.getRestaurantReadRepo().getById(order.getRestaurant());
			Shopper shopperInDataBase = uow.getShopperReadRepo().getById(order.getUsername());
			
			if (restInDataBase == null || restInDataBase.isDeleted() ||
					shopperInDataBase == null || shopperInDataBase.isDeleted()) {
				continue;
			}
			
			order.setDate(new Date());
			order.setId(UUID.randomUUID());
			
			uow.getOrderWriteRepo().add(order);
			succededNumber++;
		}
		
		if (succededNumber == orders.size()) {
			return DatabaseErrors.OK;
		} else if (succededNumber == 0) {
			return DatabaseErrors.FAILED;
		} else {
			return DatabaseErrors.PARTIAL;
		}
	}
}

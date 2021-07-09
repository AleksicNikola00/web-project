package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import beans.enumerations.OrderStatus;
import beans.errors.DatabaseErrors;
import beans.model.Order;
import beans.model.Restaurant;
import beans.model.Shopper;
import beans.model.ShopperType;

public class CRUDOrderService extends BaseService {
	
	public CRUDOrderService(String path) {
		super(path);
	}
	
	//Testing
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
		
		Shopper shopper = uow.getShopperReadRepo().getById(order.getUsername());
		
		subtractPointsForUser(shopper, order);
		
		return DatabaseErrors.NO_ERROR;
	}
	
	public String requestOrder(UUID id,String username) {
		Order order = uow.getOrderReadRepo().getById(id);
		
		if (order.getStatus() != OrderStatus.WAITING_DELIVERY) {
			return DatabaseErrors.WRONG_ORDER_STATUS;
		}
		
		order.setStatus(OrderStatus.PENDING_DELIVERY);
		order.setDeliveryWorkerUsername(username);
		
		uow.getOrderWriteRepo().update(order);
		
		return DatabaseErrors.NO_ERROR;
	}
	
	public String delieveredOrder(UUID id) {
		Order order = uow.getOrderReadRepo().getById(id);
		
		if (order.getStatus() != OrderStatus.IN_TRANSPORT) {
			return DatabaseErrors.WRONG_ORDER_STATUS;
		}
		
		order.setStatus(OrderStatus.DELIEVERED);
		
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
			
			addPointsForUser(shopperInDataBase, order);
			
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
	
	private void addPointsForUser(Shopper shopper, Order order) {
		int newPoints = (int) (shopper.getCollectedPoints() + order.getPrice() * 133 / 1000);
		
		shopper.setCollectedPoints(newPoints);
		
		calculateShopperType(shopper);
	}
	
	private void calculateShopperType(Shopper shopper) {
		ShopperType currentType = uow.getShopperTypeReadRepo().getById(shopper.getShopperType());
		
		ArrayList<ShopperType> allTypes = uow.getShopperTypeReadRepo().getAll();
		
		for(int i = 0; i < allTypes.size(); i++) {
			if (i == allTypes.size() - 1) {
				shopper.setShopperType(allTypes.get(i).getType());
				break;
			}
			
			if (allTypes.get(i).getMaximumPoints() > shopper.getCollectedPoints() &&
					allTypes.get(i).getRequiredPoints() <= shopper.getCollectedPoints()) {
				shopper.setShopperType(allTypes.get(i).getType());
				break;
			}
		}
		
		uow.getShopperWriteRepo().update(shopper);
	}
	
	private void subtractPointsForUser(Shopper shopper, Order order) {
		int newPoints = (int) (shopper.getCollectedPoints() - order.getPrice() * 133 * 4 / 1000);
		
		if (newPoints < 0) {
			newPoints = 0;
		}
		
		shopper.setCollectedPoints(newPoints);
		
		calculateShopperType(shopper);
	}
}

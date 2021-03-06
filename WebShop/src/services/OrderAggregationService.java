package services;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import beans.enumerations.OrderStatus;
import beans.model.Item;
import beans.model.Order;
import beans.model.OrderItemIDAmount;
import beans.model.Restaurant;
import dto.PastOrderDTO;
import dto.PastOrderedItemDTO;
import repository.DatabaseConstants;

public class OrderAggregationService extends BaseService {

	
	public OrderAggregationService(String path) {
		super(path);
	}
	
	public ArrayList<PastOrderDTO> getPastOrdersForRestaurant(UUID restaurantID){
		ArrayList<PastOrderDTO> ret = new ArrayList<PastOrderDTO>();

		ArrayList<Order> orders = uow.getOrderReadRepo().getAll();
		
		for(Order order : orders) {
			
			if (order.isDeleted() || !restaurantID.equals(order.getRestaurant())) {
				continue;
			}
			
			PastOrderDTO current = new PastOrderDTO();
			current.setId(order.getId());
			current.setItems(new ArrayList<PastOrderedItemDTO>());
			
			ArrayList<Item> items = uow.getItemReadRepo().getAll();
			for(OrderItemIDAmount item : order.getOrderedItems()) {
				Item correlatingItem = findItem(items, item.getId());
				PastOrderedItemDTO poi = new PastOrderedItemDTO();
				poi.setName(correlatingItem.getName());
				poi.setAmount(item.getAmount());
				poi.setPrice(correlatingItem.getPrice());
				
				current.getItems().add(poi);
			}
			
			Restaurant restaurant = uow.getRestaurantReadRepo().getById(order.getRestaurant());
			File file = new File(uow.getDatabasePath() + DatabaseConstants.RESTAURANTS_LOGO_PATH + restaurant.getId() + ".png");
			current.setLogoPath(DatabaseConstants.encodeBase64(file));
			current.setRestaurantName(restaurant.getName());
			current.setDate(buildDate(order.getDate()));
			current.setStatus(order.getStatus());
			current.setRestaurantType(restaurant.getType());
			current.setPrice(order.getPrice());
			
			ret.add(current);
		}
		
		return ret;
	}
	
	public ArrayList<PastOrderDTO> getPastOrdersForWorker(String username){
		ArrayList<PastOrderDTO> ret = new ArrayList<PastOrderDTO>();
		
		ArrayList<Order> orders = uow.getOrderReadRepo().getAll();
		
		for(Order order : orders) {
			
			if (order.isDeleted() || !(username.equals(order.getDeliveryWorkerUsername()) || order.getStatus().equals(OrderStatus.WAITING_DELIVERY))) {
				continue;
			}
			
			PastOrderDTO current = new PastOrderDTO();
			current.setId(order.getId());
			current.setItems(new ArrayList<PastOrderedItemDTO>());
			
			ArrayList<Item> items = uow.getItemReadRepo().getAll();
			for(OrderItemIDAmount item : order.getOrderedItems()) {
				Item correlatingItem = findItem(items, item.getId());
				PastOrderedItemDTO poi = new PastOrderedItemDTO();
				poi.setName(correlatingItem.getName());
				poi.setAmount(item.getAmount());
				poi.setPrice(correlatingItem.getPrice());
				
				current.getItems().add(poi);
			}
			
			Restaurant restaurant = uow.getRestaurantReadRepo().getById(order.getRestaurant());
			File file = new File(uow.getDatabasePath() + DatabaseConstants.RESTAURANTS_LOGO_PATH + restaurant.getId() + ".png");
			current.setLogoPath(DatabaseConstants.encodeBase64(file));
			current.setRestaurantName(restaurant.getName());
			current.setDate(buildDate(order.getDate()));
			current.setStatus(order.getStatus());
			current.setRestaurantType(restaurant.getType());
			current.setPrice(order.getPrice());
			
			ret.add(current);
		}
		
		return ret;
	}
	
	
	public ArrayList<PastOrderDTO> getPastOrdersForUser(String username){
		ArrayList<PastOrderDTO> ret = new ArrayList<PastOrderDTO>();
		
		ArrayList<Order> orders = uow.getOrderReadRepo().getAll();
		
		for(Order order : orders) {
			if (order.isDeleted() || !order.getUsername().equals(username)) {
				continue;
			}
			
			PastOrderDTO current = new PastOrderDTO();
			current.setId(order.getId());
			current.setItems(new ArrayList<PastOrderedItemDTO>());
			
			ArrayList<Item> items = uow.getItemReadRepo().getAll();
			for(OrderItemIDAmount item : order.getOrderedItems()) {
				Item correlatingItem = findItem(items, item.getId());
				PastOrderedItemDTO poi = new PastOrderedItemDTO();
				poi.setName(correlatingItem.getName());
				poi.setAmount(item.getAmount());
				poi.setPrice(correlatingItem.getPrice());
				
				current.getItems().add(poi);
			}
			
			Restaurant restaurant = uow.getRestaurantReadRepo().getById(order.getRestaurant());
			File file = new File(uow.getDatabasePath() + DatabaseConstants.RESTAURANTS_LOGO_PATH + restaurant.getId() + ".png");
			current.setLogoPath(DatabaseConstants.encodeBase64(file));
			current.setRestaurantName(restaurant.getName());
			current.setDate(buildDate(order.getDate()));
			current.setStatus(order.getStatus());
			current.setRestaurantType(restaurant.getType());
			current.setPrice(order.getPrice());
			
			ret.add(current);
		}
		
		return ret;
	}
	
	private Item findItem(ArrayList<Item> items, UUID id) {
		for(Item i : items) {
			if (i.getId().equals(id)) {
				return i;
			}
		}
		
		return null;
	}
	
	@SuppressWarnings("deprecation")
	private String buildDate(Date date) {
		return date.getHours() + ":" + date.getMinutes() + " " + (date.getMonth() + 1) + "-"
				+ date.getDate() + "-" + (date.getYear() + 1900);
	}
}

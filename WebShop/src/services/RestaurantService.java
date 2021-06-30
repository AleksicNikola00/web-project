package services;

import java.util.ArrayList;
import java.util.UUID;

import beans.errors.DatabaseErrors;
import beans.model.Restaurant;

public class RestaurantService extends BaseService {
	
	public RestaurantService(String path) {
		super(path);
	}
	
	public String add(Restaurant restaurant) {
		Restaurant foundInDatabase = uow.getRestaurantReadRepo().getById(restaurant.getId());
		
		if (foundInDatabase == null) {
			uow.getRestaurantWriteRepo().add(restaurant);
			return DatabaseErrors.NO_ERROR;
		}
		else if (foundInDatabase.isDeleted()) {
			uow.getRestaurantWriteRepo().update(restaurant);
			return DatabaseErrors.NO_ERROR;
		}
		
		return DatabaseErrors.ALREADY_EXISTS;
	}
	
	public String update(Restaurant restaurant) {
		Restaurant foundInDatabase = uow.getRestaurantReadRepo().getById(restaurant.getId());
		if (foundInDatabase == null) {
			return DatabaseErrors.NOT_FOUND;
		}
		
		uow.getRestaurantWriteRepo().update(restaurant);
		return DatabaseErrors.NO_ERROR;
	}
	
	public String delete(Restaurant restaurant) {
		Restaurant foundInDatabase = uow.getRestaurantReadRepo().getById(restaurant.getId());
		
		if (foundInDatabase == null) {
			return DatabaseErrors.NOT_FOUND;
		}
		else if (foundInDatabase.isDeleted()) {
			return DatabaseErrors.ALREADY_DELETED;
		}
		
		uow.getRestaurantWriteRepo().delete(restaurant);
		return DatabaseErrors.NO_ERROR;
	}
	
	public Restaurant getById(UUID id) {
		return uow.getRestaurantReadRepo().getById(id);
	}
	
	public ArrayList<Restaurant> getAll(){
		return uow.getRestaurantReadRepo().getAll();
	}
}
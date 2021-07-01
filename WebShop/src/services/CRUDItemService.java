package services;

import java.util.UUID;

import beans.errors.DatabaseErrors;
import beans.model.Item;
import beans.model.Restaurant;
import repository.DatabaseConstants;

public class CRUDItemService extends BaseService {
	
	public CRUDItemService(String path) {
		super(path);
	}
	
	public String add(Item item) {
		
		Restaurant restaurant = uow.getRestaurantReadRepo().getById(item.getRestaurantId());
		if (restaurant == null || restaurant.isDeleted()) {
			return DatabaseErrors.NO_RESTAURANT_FOUND;
		}
		
		item.setId(UUID.randomUUID());
		
		if (item.getPicturePath() == null || item.getPicturePath().trim().equals("")) {
			item.setPicturePath(DatabaseConstants.ITEM_LOGO_PATH);
		}
		
		uow.getItemWriteRepo().add(item);
		
		
		return DatabaseErrors.NO_ERROR;
	}
}

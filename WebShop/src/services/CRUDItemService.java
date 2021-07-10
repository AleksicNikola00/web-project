package services;

import java.io.File;
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
		
		
		
		File file = new File(uow.getDatabasePath() + DatabaseConstants.ITEM_LOGO_PATH + item.getId() + ".png");
		DatabaseConstants.writeEncodedBase64(file, item.getPicturePath());
		
		item.setPicturePath("");
		uow.getItemWriteRepo().add(item);
		
		
		return DatabaseErrors.NO_ERROR;
	}
	
	public String edit(Item item) {
		if(uow.getItemReadRepo().getById(item.getId())==null) return DatabaseErrors.NOT_FOUND;
		
		if(item.getPicturePath() != null && !item.getPicturePath().equals("")) {
			File file = new File(uow.getDatabasePath() + DatabaseConstants.ITEM_LOGO_PATH + item.getId() + ".png");
			DatabaseConstants.writeEncodedBase64(file, item.getPicturePath());
		}
		
		item.setPicturePath("");
		
		uow.getItemWriteRepo().update(item);
		return DatabaseErrors.NO_ERROR;
	}
}

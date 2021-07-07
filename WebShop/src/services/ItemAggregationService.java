package services;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import beans.model.Item;
import repository.DatabaseConstants;

public class ItemAggregationService extends BaseService {
	
	public ItemAggregationService(String path) {
		super(path);
	}
	
	public ArrayList<Item> getItemsForRestaurantId(UUID id){
		ArrayList<Item> items = new ArrayList<Item>();
		
		ArrayList<Item> allItems = uow.getItemReadRepo().getAll();
		for (Item item : allItems) {
			if (item.isDeleted() || !item.getRestaurantId().equals(id)) {
				continue;
			}
			
			File picutre = new File(uow.getDatabasePath() + DatabaseConstants.ITEM_LOGO_PATH + item.getId() + ".png");
			
			item.setPicturePath(DatabaseConstants.encodeBase64(picutre));
			items.add(item);
		}
		
		return items;
	}
	
	public ArrayList<Item> getAllItemsForRestaurantId(UUID id){
		ArrayList<Item> items = new ArrayList<Item>();
		
		ArrayList<Item> allItems = uow.getItemReadRepo().getAll();
		for (Item item : allItems) {
			if (!item.getRestaurantId().equals(id)) {
				continue;
			}
			
			File picutre = new File(uow.getDatabasePath() + DatabaseConstants.ITEM_LOGO_PATH + item.getId() + ".png");
			
			item.setPicturePath(DatabaseConstants.encodeBase64(picutre));
			items.add(item);
		}
		
		return items;
	}
}

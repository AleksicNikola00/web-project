package services;

import java.util.ArrayList;
import java.util.UUID;

import beans.model.Item;

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
			
			item.setPicturePath(item.getPicturePath() + "/" + item.getId() + ".png");
			items.add(item);
		}
		
		return items;
	}
}

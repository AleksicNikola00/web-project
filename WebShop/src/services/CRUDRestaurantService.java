package services;

import java.util.UUID;

import beans.errors.DatabaseErrors;
import beans.model.City;
import beans.model.GeoLocation;
import beans.model.Restaurant;

public class CRUDRestaurantService extends BaseService {
	
	public CRUDRestaurantService(String path) {
		super(path);
	}
	
	public String addRestaurant(Restaurant restaurant, GeoLocation geoLoc, City city) {
		
		restaurant.setId(UUID.randomUUID());
		geoLoc.setId(UUID.randomUUID());
		restaurant.setGeoLocationId(geoLoc.getId());
		
		City cityFromDatabase = uow.getCityReadRepo().getCityByName(city.getName());
		if (cityFromDatabase == null) {
			city.setId(UUID.randomUUID());
			uow.getCityWriteRepo().add(city);
			
			cityFromDatabase = city;
		}
		
		geoLoc.setCityId(cityFromDatabase.getId());
		
		uow.getRestaurantWriteRepo().add(restaurant);
		uow.getGeoLocationWriteRepo().add(geoLoc);
		
		return DatabaseErrors.NO_ERROR;
	}
}

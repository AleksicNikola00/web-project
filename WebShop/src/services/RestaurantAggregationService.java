package services;

import java.io.File;
import java.util.ArrayList;

import beans.enumerations.RestaurantStatus;
import beans.model.City;
import beans.model.GeoLocation;
import beans.model.Restaurant;
import dto.RestaurantsDTO;
import repository.DatabaseConstants;

public class RestaurantAggregationService extends BaseService {

	public RestaurantAggregationService(String path) {
		super(path);
	}
	
	public ArrayList<RestaurantsDTO> getRestaurantsAggregated(){
		ArrayList<RestaurantsDTO> ret = new ArrayList<RestaurantsDTO>();
		
		ArrayList<Restaurant> allRestaurants = uow.getRestaurantReadRepo().getAll();
		
		for (Restaurant r : allRestaurants) {
			
			GeoLocation geoloc = uow.getGeoLocationReadRepo().getById(r.getGeoLocationId());
			City city = uow.getCityReadRepo().getById(geoloc.getCityId());
			
			RestaurantsDTO current = new RestaurantsDTO();
			current.setName(r.getName());
			current.setType(r.getType());
			current.setRating(r.getRating());
			current.setAddress(geoloc.getStreetName() + " " + geoloc.getNumber());
			current.setCity(city.getName());
			current.setOpen(r.getStatus() == RestaurantStatus.OPEN);
			current.setId(r.getId());
			current.setX(geoloc.getX());
			current.setY(geoloc.getY());
			
			File picutre = new File(uow.getDatabasePath() + DatabaseConstants.RESTAURANTS_LOGO_PATH + r.getId() + ".png");
			
			current.setLogoPath(DatabaseConstants.encodeBase64(picutre));
			
			ret.add(current);
		}
		
		return ret;
	}
	
}

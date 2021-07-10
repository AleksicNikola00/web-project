package services;

import java.io.File;
import java.util.ArrayList;

import beans.enumerations.RestaurantStatus;
import beans.model.City;
import beans.model.GeoLocation;
import beans.model.Manager;
import beans.model.Restaurant;
import dto.AdminViewRestaurantsDTO;
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
			
			if (r.isDeleted()) {
				continue;
			}
			
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
	
	public RestaurantsDTO getRestaurantByManager(String managerId) {
		RestaurantsDTO retRest = new RestaurantsDTO();
		ArrayList<Restaurant> restaurants = uow.getRestaurantReadRepo().getAll();
		for(Restaurant r : restaurants)
		{
			if(r.getManagerId().equals(managerId))
			{
				GeoLocation geoloc = uow.getGeoLocationReadRepo().getById(r.getGeoLocationId());
				City city = uow.getCityReadRepo().getById(geoloc.getCityId());
				
				retRest.setName(r.getName());
				retRest.setType(r.getType());
				retRest.setRating(r.getRating());
				retRest.setAddress(geoloc.getStreetName() + " " + geoloc.getNumber());
				retRest.setCity(city.getName());
				retRest.setOpen(r.getStatus() == RestaurantStatus.OPEN);
				retRest.setId(r.getId());
				retRest.setX(geoloc.getX());
				retRest.setY(geoloc.getY());
				
				File picutre = new File(uow.getDatabasePath() + DatabaseConstants.RESTAURANTS_LOGO_PATH + r.getId() + ".png");
				retRest.setLogoPath(DatabaseConstants.encodeBase64(picutre));
	
				break;
			}
		}
		
		return retRest;
	}
	
	public ArrayList<AdminViewRestaurantsDTO> getAdminRestaurantsAggregated(){
		ArrayList<AdminViewRestaurantsDTO> ret = new ArrayList<AdminViewRestaurantsDTO>();
		
		ArrayList<Restaurant> restaurants = uow.getRestaurantReadRepo().getAll();
		
		for(Restaurant rest : restaurants) {
			
			AdminViewRestaurantsDTO curr = new AdminViewRestaurantsDTO();
			
			curr.setName(rest.getName());
			curr.setType(rest.getType());
			curr.setRating(rest.getRating());
			curr.setId(rest.getId());
			
			Manager manager = uow.getManagerReadRepo().getById(rest.getManagerId());
			
			curr.setManager(manager.getName() + " " + manager.getSurname());
			curr.setManagerId(manager.getUsername());
			
			GeoLocation geoLoc = uow.getGeoLocationReadRepo().getById(rest.getGeoLocationId());
			
			curr.setLocation(geoLoc.getStreetName() + ", " + geoLoc.getNumber());
			curr.setStatus(rest.getStatus());
			curr.setGeoLocation(geoLoc.getX() + ", " + geoLoc.getY());
			
			File image = new File(uow.getDatabasePath() + DatabaseConstants.RESTAURANTS_LOGO_PATH + rest.getId() + ".png");
			curr.setLogoPath(DatabaseConstants.encodeBase64(image));
			
			ret.add(curr);
		}
		
		return ret;
	}
	
}

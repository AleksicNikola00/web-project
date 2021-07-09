package services;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import beans.enumerations.RestaurantStatus;
import beans.errors.DatabaseErrors;
import beans.model.City;
import beans.model.GeoLocation;
import beans.model.Manager;
import beans.model.Restaurant;
import dto.AdminViewRestaurantsDTO;
import repository.DatabaseConstants;

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

	public String linkRestaurantAndManager(String username, UUID restaurantId) {

		Manager manager = uow.getManagerReadRepo().getById(username);
		Restaurant restaurant = uow.getRestaurantReadRepo().getById(restaurantId);

		if (manager == null || manager.isDeleted() ||
				restaurant == null || restaurant.isDeleted()) {
			return DatabaseErrors.NOT_FOUND;
		}

		restaurant.setManagerId(username);
		uow.getRestaurantWriteRepo().update(restaurant);

		return DatabaseErrors.NO_ERROR;
	}

	public String createNewRestaurant(AdminViewRestaurantsDTO newRestaurant) {

		//Create city

		ArrayList<City> cities = uow.getCityReadRepo().getAll();
		City city = null;
		if (cities != null || cities.size() != 0) {
			for(City c : cities) {
				if (c.getName().equalsIgnoreCase("novi sad")) {
					city = c;
					break;
				}
			}
		}

		if (city == null) {
			city = new City();
			city.setName("Novi Sad");
			city.setPostalCode(21000);
			city.setCountry("Serbia");
			city.setId(UUID.randomUUID());
			uow.getCityWriteRepo().add(city);
		}

		//Create geoLoc
		if (!newRestaurant.getLocation().contains(",")) {
			System.out.println(newRestaurant.getLocation());
			return DatabaseErrors.FAILED;
		}
		if (!newRestaurant.getGeoLocation().contains(",")) {
			System.out.println(newRestaurant.getGeoLocation());
			return DatabaseErrors.FAILED;
		}


		GeoLocation geoLoc = new GeoLocation();

		try {
			String[] parts = newRestaurant.getGeoLocation().split(", ");

			geoLoc.setX(Double.parseDouble(parts[0]));
			geoLoc.setY(Double.parseDouble(parts[1]));
		} catch(Exception e) {
			System.out.println(newRestaurant.getLocation());
			return DatabaseErrors.FAILED;
		}

		geoLoc.setStreetName(newRestaurant.getLocation().split(", ")[0]);
		try {
			geoLoc.setNumber(Integer.parseInt(newRestaurant.getLocation().split(", ")[1].trim()));
		} catch(Exception e) {
			System.out.println(newRestaurant.getLocation());
			return DatabaseErrors.FAILED;
		}
		geoLoc.setId(UUID.randomUUID());
		geoLoc.setCityId(city.getId());

		//Create restaurant
		Restaurant rest = new Restaurant();
		rest.setName(newRestaurant.getName());
		rest.setId(UUID.randomUUID());
		rest.setType(newRestaurant.getType());
		rest.setStatus(RestaurantStatus.OPEN);
		rest.setGeoLocationId(geoLoc.getId());
		rest.setManagerId(newRestaurant.getManagerId());
		rest.setRating(1.0);

		if (uow.getManagerReadRepo().getById(rest.getManagerId()) == null ||
				uow.getRestaurantReadRepo().getRestaurantByManagerUsername(rest.getManagerId()) != null) {
			return DatabaseErrors.FAILED;
		}

		File file = new File(uow.getDatabasePath() + DatabaseConstants.RESTAURANTS_LOGO_PATH + rest.getId() + ".png");
		DatabaseConstants.writeEncodedBase64(file, newRestaurant.getLogoPath());

		uow.getGeoLocationWriteRepo().add(geoLoc);
		uow.getRestaurantWriteRepo().add(rest);

		return DatabaseErrors.NO_ERROR;
	}

	public String editRestaurant(AdminViewRestaurantsDTO rest) {
		
		Restaurant restInDb = uow.getRestaurantReadRepo().getById(rest.getId());
		
		if (restInDb == null) {
			return DatabaseErrors.FAILED;
		}
		
		//Create city
		ArrayList<City> cities = uow.getCityReadRepo().getAll();
		City city = null;
		if (cities != null || cities.size() != 0) {
			for(City c : cities) {
				if (c.getName().equalsIgnoreCase("novi sad")) {
					city = c;
					break;
				}
			}
		}

		if (city == null) {
			city = new City();
			city.setName("Novi Sad");
			city.setPostalCode(21000);
			city.setCountry("Serbia");
			city.setId(UUID.randomUUID());
			uow.getCityWriteRepo().add(city);
		}

		//Create geoLoc
		if (!rest.getLocation().contains(",")) {
			System.out.println(rest.getLocation());
			return DatabaseErrors.FAILED;
		}
		if (!rest.getGeoLocation().contains(",")) {
			System.out.println(rest.getGeoLocation());
			return DatabaseErrors.FAILED;
		}
		
		GeoLocation geoLoc = uow.getGeoLocationReadRepo().getById(restInDb.getGeoLocationId());
		try {
			String[] parts = rest.getGeoLocation().split(", ");

			geoLoc.setX(Double.parseDouble(parts[0]));
			geoLoc.setY(Double.parseDouble(parts[1]));
		} catch(Exception e) {
			System.out.println(rest.getLocation());
			return DatabaseErrors.FAILED;
		}

		geoLoc.setStreetName(rest.getLocation().split(", ")[0]);
		try {
			geoLoc.setNumber(Integer.parseInt(rest.getLocation().split(", ")[1].trim()));
		} catch(Exception e) {
			System.out.println(rest.getLocation());
			return DatabaseErrors.FAILED;
		}
		geoLoc.setCityId(city.getId());
		
		restInDb.setName(rest.getName());
		restInDb.setType(rest.getType());
		
		if (!rest.getLogoPath().isEmpty()) {
			File file = new File(uow.getDatabasePath() + DatabaseConstants.RESTAURANTS_LOGO_PATH + rest.getId() + ".png");
			DatabaseConstants.writeEncodedBase64(file, rest.getLogoPath());
		}
		
		uow.getGeoLocationWriteRepo().update(geoLoc);
		uow.getRestaurantWriteRepo().update(restInDb);
		
		return DatabaseErrors.NO_ERROR;
	}
}

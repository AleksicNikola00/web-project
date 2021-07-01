package controllers;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.enumerations.RestaurantStatus;
import beans.model.City;
import beans.model.Comment;
import beans.model.GeoLocation;
import beans.model.Item;
import beans.model.Restaurant;
import dto.NewRestaurantDTO;
import services.CRUDItemService;
import services.CRUDRestaurantService;

@Path("/test")
public class TestController {
	
	@Context
	ServletContext ctx;
	
	
	//Adding restaurants
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addRestaurant(NewRestaurantDTO newRestaurant) {
		Restaurant restaurant = new Restaurant();
		restaurant.setName(newRestaurant.getName());
		restaurant.setType(newRestaurant.getType());
		restaurant.setStatus(RestaurantStatus.OPEN);
		restaurant.setLogoPath("");
		restaurant.setRating(1);
		
		GeoLocation geoLocation = new GeoLocation();
		geoLocation.setX(newRestaurant.getX());
		geoLocation.setY(newRestaurant.getY());
		geoLocation.setStreetName(newRestaurant.getStreetname());
		geoLocation.setNumber(newRestaurant.getNumber());
		
		City city = new City();
		city.setName(newRestaurant.getCityName());
		city.setCountry(newRestaurant.getCountry());
		
		CRUDRestaurantService service = new CRUDRestaurantService(ctx.getRealPath(""));
		return service.addRestaurant(restaurant, geoLocation, city);
		
	}
	
	//Adding items in restaurants 
	@PUT
	@Path("/newitem")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addItem(Item item) {
		CRUDItemService service = new CRUDItemService(ctx.getRealPath(""));
		
		return service.add(item);
	}
	
	//Adding comments for restaurants
	@PUT
	@Path("/newComment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addComment(Comment comment) {
		return "";
	}
}
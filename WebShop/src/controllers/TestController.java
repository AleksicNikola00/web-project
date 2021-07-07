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
import beans.model.Order;
import beans.model.Restaurant;
import beans.model.ShopperType;
import dto.NewRestaurantDTO;
import dto.NewUser;
import services.CRUDAdminService;
import services.CRUDCommentService;
import services.CRUDItemService;
import services.CRUDManagerService;
import services.CRUDOrderService;
import services.CRUDRestaurantService;
import services.CRUDShopperTypeService;

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
		CRUDCommentService service = new CRUDCommentService(ctx.getRealPath(""));
		
		return service.add(comment);		
	}
	
	//Adding past orders 
	@PUT
	@Path("/order")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addOrder(Order order) {
		CRUDOrderService service = new CRUDOrderService(ctx.getRealPath(""));
		
		return service.add(order);
	}
	
	//Adding shopperTypes
	@PUT
	@Path("/shopperType")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addShopperType(ShopperType shopperType) {
		CRUDShopperTypeService service = new CRUDShopperTypeService(ctx.getRealPath(""));
		
		return service.add(shopperType);
	}
	
	//Adding admins
	@PUT
	@Path("/admin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addAdmin(NewUser admin) {
		CRUDAdminService service = new CRUDAdminService(ctx.getRealPath(""));
		
		return service.addAdmin(admin);
	}
	
	//Adding managers
	@PUT
	@Path("/manager")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addManager(NewUser manager) {
		CRUDManagerService service = new CRUDManagerService(ctx.getRealPath(""));
		
		return service.add(manager);
	}
}
package controllers;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.model.City;
import beans.model.Restaurant;
import services.CityService;
import services.RestaurantService;

@Path("/test")
public class TestController {
	
	@Context
	ServletContext ctx;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String add(Restaurant restaurant) {
		if (restaurant.getId() == null) {
			restaurant.setId(UUID.randomUUID());
		}
		RestaurantService service = new RestaurantService(ctx.getRealPath(""));
		
		return service.add(restaurant);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(Restaurant restaurant) {
		if (restaurant.getId() == null) {
			return "Restaurant id is required for updateing";
		}
		RestaurantService service = new RestaurantService(ctx.getRealPath(""));
		
		return service.update(restaurant);
	}
	
	@PUT
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public String delete(Restaurant restaurant) {
		if (restaurant.getId() == null) {
			return "Restaurant id is required for deleting";
		}
		RestaurantService service = new RestaurantService(ctx.getRealPath(""));
		
		return service.delete(restaurant);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Restaurant> getAll(){
		RestaurantService service = new RestaurantService(ctx.getRealPath(""));
		
		return service.getAll();
	}
	
	@GET
	@Path("/id")
	@Produces(MediaType.APPLICATION_JSON)
	public Restaurant getById(UUID id) {
		RestaurantService service = new RestaurantService(ctx.getRealPath(""));
		
		return service.getById(id);
	}
}
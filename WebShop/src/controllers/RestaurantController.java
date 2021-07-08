package controllers;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.enumerations.RestaurantType;
import dto.AdminViewRestaurantsDTO;
import dto.RestaurantsDTO;
import services.CRUDRestaurantService;

@Path("/restaurant")
public class RestaurantController {

	@Context
	ServletContext ctx;
	
	@POST
	@Path("/linkwithrestaurant/{managerId}/{restaurantId}")
	public String linkManagerAndRestaurant(@PathParam("managerId") String managerUsername, @PathParam("restaurantId") UUID restaurantId) {
		
		CRUDRestaurantService service = new CRUDRestaurantService(ctx.getRealPath(""));
		
		return service.linkRestaurantAndManager(managerUsername, restaurantId);
	}
	
	@POST
	@Path("/addrestaurant")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String createNewRestaurant(AdminViewRestaurantsDTO rest) {
		CRUDRestaurantService service = new CRUDRestaurantService(ctx.getRealPath(""));
		
		return service.createNewRestaurant(rest);
	}
	
	@POST
	@Path("/editrestaurant")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String editRestaurant(AdminViewRestaurantsDTO rest) {
		CRUDRestaurantService service = new CRUDRestaurantService(ctx.getRealPath(""));
		
		return service.editRestaurant(rest);
	}
	
	@GET
	@Path("/gettypes")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<RestaurantType> getTypes() {
		ArrayList<RestaurantType> ret = new ArrayList<RestaurantType>();
		
		for(RestaurantType rt : RestaurantType.values()) {
			ret.add(rt);
		}
		
		return ret;
	}
}

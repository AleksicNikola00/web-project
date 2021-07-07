package controllers;

import java.util.UUID;

import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

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
}

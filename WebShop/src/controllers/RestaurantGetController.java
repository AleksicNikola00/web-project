package controllers;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.RestaurantsDTO;
import services.RestaurantAggregationService;

@Path("/getrestaurants")
public class RestaurantGetController {
	
	@Context
	ServletContext ctx;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<RestaurantsDTO> getBasicRestaurantInfo(){		
		RestaurantAggregationService service = new RestaurantAggregationService(ctx.getRealPath(""));
		
		return service.getRestaurantsAggregated();
	}
}

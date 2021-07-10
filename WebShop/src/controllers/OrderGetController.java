package controllers;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.PastOrderDTO;
import services.OrderAggregationService;

@Path("/getorders")
public class OrderGetController {

	@Context
	ServletContext ctx;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PastOrderDTO> getPastOrdersForId(@PathParam("id") String id){
		OrderAggregationService service = new OrderAggregationService(ctx.getRealPath(""));
		
		return service.getPastOrdersForUser(id);
	}
	
	@GET
	@Path("/worker/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PastOrderDTO> getPastOrdersForWorker(@PathParam("id") String id){
		OrderAggregationService service = new OrderAggregationService(ctx.getRealPath(""));
		
		return service.getPastOrdersForWorker(id);
	}
	
	@GET
	@Path("/manager/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PastOrderDTO> getOrdersForRestaurant(@PathParam("id") UUID restaurantID){
		OrderAggregationService service = new OrderAggregationService(ctx.getRealPath(""));
		
		return service.getPastOrdersForRestaurant(restaurantID);
	}
}

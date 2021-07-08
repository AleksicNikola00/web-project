package controllers;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.model.Item;
import services.ItemAggregationService;

@Path("/getitemsforrestaurant")
public class ItemGetController {

	@Context
	ServletContext ctx;
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Item> getItemsFroRestaurant(@PathParam("id") UUID id){
		ItemAggregationService service = new ItemAggregationService(ctx.getRealPath(""));
		
		return service.getItemsForRestaurantId(id);
	}
	
	@GET
	@Path("/all/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Item> getAllItemsFroRestaurant(@PathParam("id") UUID id){
		ItemAggregationService service = new ItemAggregationService(ctx.getRealPath(""));
		
		return service.getAllItemsForRestaurantId(id);
	}
}

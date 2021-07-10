package controllers;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.model.Item;
import services.CRUDItemService;
import services.ItemAggregationService;

@Path("/item")
public class ItemController {
	@Context
	ServletContext ctx;
	
	@PUT
	@Path("/newitem")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addItem(Item item) {
		CRUDItemService service = new CRUDItemService(ctx.getRealPath(""));
		
		return service.add(item);
	}
	
	@PUT
	@Path("/edititem")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String editItem(Item item) {
		CRUDItemService service = new CRUDItemService(ctx.getRealPath(""));
		
		return service.edit(item);
	}
	
	
	
	
	
	
}

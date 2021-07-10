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

import dto.ShopperDTO;
import services.ManagerService;





@Path("/manager")
public class ManagerController {
	
	@Context
	ServletContext ctx;
	
	@GET
	@Path("/shoppers/{restaurantId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ShopperDTO> getShoppers(@PathParam("restaurantId")UUID restaurantID){
		ManagerService service = new ManagerService(ctx.getRealPath(""));
		
		return service.getShoppers(restaurantID);
	}
	
}

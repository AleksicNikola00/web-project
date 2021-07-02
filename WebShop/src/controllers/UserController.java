package controllers;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.enumerations.TypeOfShopper;
import beans.model.ShopperType;
import dto.LoggedInUser;
import dto.NewShopper;
import services.CRUDShopperTypeService;
import services.UserService;

@Path("/user")
public class UserController {

	@Context
	ServletContext ctx;
	
	@PUT
	@Path("/updateshopper")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LoggedInUser updateAccountInfo(NewShopper shopper) {
		UserService service = new UserService(ctx.getRealPath(""));
		
		return service.updateShopperInfo(shopper);
	}
	
	@GET
	@Path("/getshoppertype/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public ShopperType getShopperType(@PathParam("type") TypeOfShopper type) {
		CRUDShopperTypeService service = new CRUDShopperTypeService(ctx.getRealPath(""));
		
		return service.getShopperType(type);
	}
}

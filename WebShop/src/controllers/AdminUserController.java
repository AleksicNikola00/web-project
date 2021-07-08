package controllers;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.AdminDTO;
import dto.DeliveryWorkerDTO;
import dto.ManagerDTO;
import dto.ShopperDTO;
import services.AdminUserService;

@Path("/adminuser")
public class AdminUserController {

	@Context
	ServletContext ctx;
	
	@GET
	@Path("/shoppers")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ShopperDTO> getShoppers(){
		AdminUserService service = new AdminUserService(ctx.getRealPath(""));
		
		return service.getShoppers();
	}
	
	@GET
	@Path("/managers")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ManagerDTO> getManagers(){
		AdminUserService service = new AdminUserService(ctx.getRealPath(""));
		
		return service.getManagers();
	}
	
	@GET
	@Path("/deliveryworkers")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<DeliveryWorkerDTO> getDeliveryWorkers(){
		AdminUserService service = new AdminUserService(ctx.getRealPath(""));
		
		return service.getDeliveryWorkers();
	}
	
	@GET
	@Path("/admins")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<AdminDTO> getAdmins(){
		AdminUserService service = new AdminUserService(ctx.getRealPath(""));
		
		return service.getAdmins();
	}
}

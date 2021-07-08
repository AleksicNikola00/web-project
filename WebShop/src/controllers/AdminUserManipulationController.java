package controllers;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.NewUser;
import services.AdminUserManipulationService;
import services.AdminUserService;

@Path("/adminusermanipulation")
public class AdminUserManipulationController {

	@Context
	ServletContext ctx;
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addEntity(NewUser user) {
		AdminUserManipulationService service = new AdminUserManipulationService(ctx.getRealPath(""));
		
		return service.addNewUser(user);
	}
	
	@POST
	@Path("/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String editEntity(NewUser user) {

		AdminUserManipulationService service = new AdminUserManipulationService(ctx.getRealPath(""));
		
		return service.editUser(user);
	}
	
	@POST
	@Path("/editmyacc")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String editMyAccount(NewUser user) {
		AdminUserManipulationService service = new AdminUserManipulationService(ctx.getRealPath(""));
		
		return service.editMyAccount(user);
	}
	
	@GET
	@Path("/block/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String blockUser(@PathParam("id") String username) {
		AdminUserService service = new AdminUserService(ctx.getRealPath(""));
		
		return service.blockUser(username);
	}
	
	@GET
	@Path("/unblock/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String unblockUser(@PathParam("id") String username) {
		AdminUserService service = new AdminUserService(ctx.getRealPath(""));
		
		return service.unblockUser(username);
	}
}

package controllers;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.model.Admin;
import services.AdminService;

@Path("/test")
public class TestController {
	
	@Context
	ServletContext ctx;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String add(Admin admin) {
		AdminService service = new AdminService(ctx.getRealPath(""));
		
		return service.add(admin);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(Admin admin) {
		AdminService service = new AdminService(ctx.getRealPath(""));
		
		return service.update(admin);
	}
	
	@PUT
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public String delete(Admin admin) {
		AdminService service = new AdminService(ctx.getRealPath(""));
		
		return service.delete(admin);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Admin> getAll(){
		AdminService service = new AdminService(ctx.getRealPath(""));
		
		return service.getAll();
	}
	
	@GET
	@Path("/id")
	@Produces(MediaType.APPLICATION_JSON)
	public Admin getById(String username) {
		AdminService service = new AdminService(ctx.getRealPath(""));
		
		return service.getById(username);
	}
}
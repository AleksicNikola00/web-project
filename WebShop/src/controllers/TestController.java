package controllers;

import java.util.ArrayList;
import java.util.UUID;

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
import beans.model.City;
import services.AdminService;
import services.CityService;

@Path("/test")
public class TestController {
	
	@Context
	ServletContext ctx;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String add(City city) {
		CityService service = new CityService(ctx.getRealPath(""));
		
		return service.add(city);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(City city) {
		CityService service = new CityService(ctx.getRealPath(""));
		
		return service.update(city);
	}
	
	@PUT
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public String delete(City city) {
		CityService service = new CityService(ctx.getRealPath(""));
		
		return service.delete(city);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<City> getAll(){
		CityService service = new CityService(ctx.getRealPath(""));
		
		return service.getAll();
	}
	
	@GET
	@Path("/id")
	@Produces(MediaType.APPLICATION_JSON)
	public City getById(String id) {
		CityService service = new CityService(ctx.getRealPath(""));
		
		return service.getById(UUID.fromString(id));
	}
}
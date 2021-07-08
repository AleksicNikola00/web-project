package controllers;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.enumerations.RestaurantType;
import beans.enumerations.Role;
import beans.enumerations.TypeOfShopper;
import beans.errors.DatabaseErrors;
import beans.model.Credentials;
import beans.model.Manager;
import beans.model.Shopper;
import dto.NewUser;
import services.RegisterService;

@Path("/register")
public class RegisterController {
	
	@Context
	ServletContext ctx;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String registerNewShopper(NewUser newShopper) {
		
		Credentials credsPart = new Credentials();
		credsPart.setRole(Role.SHOPPER);
		credsPart.setUsername(newShopper.getUsername());
		credsPart.setPassword(newShopper.getPassword());
		
		Shopper shopperPart = new Shopper();
		shopperPart.setCollectedPoints(0);
		shopperPart.setRole(Role.SHOPPER);
		shopperPart.setName(newShopper.getName());
		shopperPart.setSurname(newShopper.getSurname());
		shopperPart.setDateOfBirth(newShopper.getDateOfBirth());
		shopperPart.setGender(newShopper.getGender());
		shopperPart.setShopperType(TypeOfShopper.BRONZE);
		shopperPart.setUsername(newShopper.getUsername());
		
		RegisterService service = new RegisterService(ctx.getRealPath(""));
		
		String result = service.registerNewShopper(credsPart, shopperPart);
		
		if (result.equals(DatabaseErrors.ALREADY_EXISTS)) {
			return "That user is already occupied!";
		}
		else {
			return "";
		}
	}
	
	@POST
	@Path("/registermanager")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String registerNewManager(NewUser newManager) {
		Credentials credsPart = new Credentials();
		Manager manager = new Manager();
		
		credsPart.setUsername(newManager.getUsername());
		credsPart.setPassword(newManager.getPassword());
		credsPart.setRole(Role.MANAGER);
		
		manager.setName(newManager.getName());
		manager.setUsername(newManager.getUsername());
		manager.setSurname(newManager.getSurname());
		manager.setRole(Role.MANAGER);
		manager.setDateOfBirth(newManager.getDateOfBirth());
		manager.setGender(newManager.getGender());
		
		RegisterService service = new RegisterService(ctx.getRealPath(""));
		
		String result = service.registerNewManager(credsPart, manager);
		
		if (result.equals(DatabaseErrors.ALREADY_EXISTS)) {
			return "That user is already occupied!";
		}
		else {
			return "";
		}
	}
	
	
}
package controllers;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.enumerations.Role;
import beans.enumerations.TypeOfShopper;
import beans.model.Credentials;
import beans.model.Shopper;
import dto.NewShopper;
import services.CredentialsService;
import services.ShopperService;

@Path("/register")
public class RegisterController {
	
	@Context
	ServletContext ctx;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String registerNewShopper(NewShopper newShopper) {
		
		CredentialsService credService = new CredentialsService(ctx.getRealPath(""));
		
		Credentials credsInDatabase = credService.getCredentials(newShopper.getUsername());
		if (credsInDatabase != null) {
			return "Username already registered";
		}
		
		credsInDatabase = new Credentials();
		credsInDatabase.setUsername(newShopper.getUsername());
		credsInDatabase.setPassword(newShopper.getPassword());
		credsInDatabase.setRole(Role.SHOPPER);
		credService.addCredentials(credsInDatabase);
		
		ShopperService shopperService = new ShopperService(ctx.getRealPath(""));
		Shopper newUser = new Shopper();
		newUser.setUsername(newShopper.getUsername());
		newUser.setName(newShopper.getName());
		newUser.setSurname(newShopper.getSurname());
		newUser.setGender(newShopper.getGender());
		newUser.setDateOfBirth(newShopper.getDateOfBirth());
		newUser.setRole(Role.SHOPPER);
		newUser.setShopperType(TypeOfShopper.BRONZE);
		
		shopperService.addShopper(newUser);
		
		return "Successful register!";
	}
	
}
package controllers;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.model.Credentials;
import beans.model.Shopper;
import services.CredentialsService;
import services.ShopperService;

@Path("/login")
public class LoginController {
	@Context
	ServletContext ctx;
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String tryLogin(Credentials user) {
		CredentialsService service = new CredentialsService(ctx.getRealPath(""));
		
		Credentials credsInDatabase = service.getCredentials(user.getUsername());
		if (credsInDatabase == null) {
			return "No such user in database";
		} else if (!credsInDatabase.getPassword().equals(user.getPassword())) {
			return "Invalid password";
		}
		
		return "Successful login!";
	}
}
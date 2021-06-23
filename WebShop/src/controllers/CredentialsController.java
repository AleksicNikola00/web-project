package controllers;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.model.Credentials;
import services.CredentialsService;

@Path("/credentials")
public class CredentialsController {
	
	@Context
	ServletContext ctx;
	
	public CredentialsController() {
	}
	
	@PostConstruct
	public void init() {
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addCredentials(Credentials creds) {
		CredentialsService service = new CredentialsService(ctx.getRealPath(""));
		service.addCredentials(creds);
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String testConnection() {
		System.out.println("Connected");
		return "Connected";
	}
}
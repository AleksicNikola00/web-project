package controllers;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import beans.enumerations.Role;
import beans.model.Credentials;
import beans.model.DeliveryWorker;
import beans.model.Shopper;
import dto.LoggedInUser;
import services.CredentialsService;
import services.DeliveryService;
import services.ShopperService;

@Path("/login")
public class LoginController {
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("deprecation")
	@POST
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
		
		LoggedInUser retUser = generateReturnUser(credsInDatabase);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		String json = "";
		
		try {
			json = mapper.writeValueAsString(retUser);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			System.out.println("Mapping failed in LogginController post method");
			e.printStackTrace();
		}
		
		return json;
	}
	
	/* private functions */
	private LoggedInUser generateReturnUser(Credentials creds) {
		
		/* add for other roles*/
		if (creds.getRole() == Role.SHOPPER) {
			return generateLoggedInShopper(creds);
		}else if(creds.getRole()== Role.DELIVERY) {
			return generateLoggedInDeliveryWorker(creds);
		}
		
		return null;
	}
	
	private LoggedInUser generateLoggedInDeliveryWorker(Credentials creds) {
		DeliveryService deliveryService = new DeliveryService(ctx.getRealPath(""));
		DeliveryWorker worker = deliveryService.getDeliveryWorker(creds.getUsername());

		LoggedInUser retWorker = new LoggedInUser();
		retWorker.setFirstname(worker.getName());
		retWorker.setLastname(worker.getSurname());
		retWorker.setUsername(worker.getUsername());
		retWorker.setDateOfBirth(worker.getDateOfBirth().getDate() + "-" + 
					(worker.getDateOfBirth().getMonth() + 1) + "-" +
					(worker.getDateOfBirth().getYear() + 1900));
		retWorker.setPoints("");
		retWorker.setPassword(creds.getPassword());
		retWorker.setGender(worker.getGender());
		retWorker.setRole(worker.getRole());
		retWorker.setShopperType(null);
		return retWorker;
	}
	
	private LoggedInUser generateLoggedInShopper(Credentials creds) {
		ShopperService shopperService = new ShopperService(ctx.getRealPath(""));
		Shopper currentShopper = shopperService.getShopper(creds.getUsername());
		
		LoggedInUser retShopper = new LoggedInUser();
		retShopper.setFirstname(currentShopper.getName());
		retShopper.setLastname(currentShopper.getSurname());
		retShopper.setUsername(currentShopper.getUsername());
		retShopper.setDateOfBirth(currentShopper.getDateOfBirth().getDate() + "-" + 
					(currentShopper.getDateOfBirth().getMonth() + 1) + "-" +
					(currentShopper.getDateOfBirth().getYear() + 1900));
		retShopper.setPoints("" + currentShopper.getCollectedPoints());
		retShopper.setPassword(creds.getPassword());
		retShopper.setGender(currentShopper.getGender());
		retShopper.setRole(currentShopper.getRole());
		retShopper.setShopperType(currentShopper.getShopperType());
		
		return retShopper;
	}
}
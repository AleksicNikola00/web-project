package services;

import beans.errors.DatabaseErrors;
import beans.model.Credentials;
import beans.model.Shopper;
import dto.LoggedInUser;
import dto.NewShopper;

public class UserService extends BaseService {
	
	
	public UserService(String path) {
		super(path);
	}
	
	public LoggedInUser updateShopperInfo(NewShopper shopper) {
		Shopper shopperInDatabase = uow.getShopperReadRepo().getById(shopper.getUsername());
		Credentials credsInDatabase = uow.getCredentialsReadRepo().getById(shopper.getUsername());
		if (shopperInDatabase == null || credsInDatabase == null) {
			return null;
		}
		
		shopperInDatabase.setName(shopper.getName());
		shopperInDatabase.setSurname(shopper.getSurname());
		shopperInDatabase.setGender(shopper.getGender());
		shopperInDatabase.setDateOfBirth(shopper.getDateOfBirth());
		
		uow.getShopperWriteRepo().update(shopperInDatabase);
		
		if (!shopper.getPassword().isEmpty()) {
			credsInDatabase.setPassword(shopper.getPassword());
			uow.getCredentialsWriteRepo().update(credsInDatabase);
		}
		
		LoggedInUser ret = generateLoggedInShopper(credsInDatabase);
		return ret;
	}
	
	private LoggedInUser generateLoggedInShopper(Credentials creds) {
		Shopper currentShopper = uow.getShopperReadRepo().getById(creds.getUsername());
		
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

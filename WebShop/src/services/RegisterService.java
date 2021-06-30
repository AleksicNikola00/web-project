package services;

import beans.errors.DatabaseErrors;
import beans.model.Credentials;
import beans.model.Shopper;

public class RegisterService extends BaseService {
	
	public RegisterService(String path) {
		super(path);
	}
	
	public String registerNewShopper(Credentials credsPart, Shopper shopperPart) {
		
		Credentials credsInDatabase = uow.getCredentialsReadRepo().getById(credsPart.getUsername());
		Shopper shopperInDatabase = uow.getShopperReadRepo().getById(shopperPart.getUsername());
		
		if (credsInDatabase != null || shopperInDatabase != null) {
			return DatabaseErrors.ALREADY_EXISTS;
		}
		
		uow.getCredentialsWriteRepo().add(credsPart);
		uow.getShopperWriteRepo().add(shopperPart);
		return DatabaseErrors.NO_ERROR;
	}
}

package services;

import beans.enumerations.Role;
import beans.errors.DatabaseErrors;
import beans.model.Credentials;
import beans.model.Manager;
import dto.NewUser;

public class CRUDManagerService extends BaseService {
	
	public CRUDManagerService(String path) {
		super(path);
	}
	
	public String add(NewUser manager) {
		
		Credentials creds = uow.getCredentialsReadRepo().getById(manager.getUsername());
		
		if (creds != null) {
			return DatabaseErrors.ALREADY_EXISTS;
		}
		
		creds = new Credentials();
		creds.setUsername(manager.getUsername());
		creds.setPassword(manager.getPassword());
		creds.setRole(Role.MANAGER);
		
		Manager newManager = new Manager();
		
		newManager.setUsername(manager.getUsername());
		newManager.setName(manager.getName());
		newManager.setSurname(manager.getSurname());
		newManager.setDateOfBirth(manager.getDateOfBirth());
		newManager.setGender(manager.getGender());
		newManager.setRole(Role.MANAGER);
		
		uow.getCredentialsWriteRepo().add(creds);
		uow.getManagerWriteRepo().add(newManager);
		
		return DatabaseErrors.NO_ERROR;
	}
}

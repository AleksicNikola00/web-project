package services;

import beans.enumerations.Role;
import beans.errors.DatabaseErrors;
import beans.model.Admin;
import beans.model.Credentials;
import dto.NewUser;

public class CRUDAdminService extends BaseService {
	
	public CRUDAdminService(String path) {
		super(path);
	}
	
	public String addAdmin(NewUser admin) {
		Credentials creds = uow.getCredentialsReadRepo().getById(admin.getUsername());
		Admin adminInDatabase = uow.getAdminReadRepo().getById(admin.getUsername());
		
		if (creds == null && adminInDatabase == null) {
			creds = new Credentials();
			creds.setUsername(admin.getUsername());
			creds.setPassword(admin.getPassword());
			creds.setRole(Role.ADMIN);
			
			adminInDatabase = new Admin();
			adminInDatabase.setName(admin.getName());
			adminInDatabase.setUsername(admin.getUsername());
			adminInDatabase.setSurname(admin.getSurname());
			adminInDatabase.setGender(admin.getGender());
			adminInDatabase.setDateOfBirth(admin.getDateOfBirth());
			adminInDatabase.setRole(Role.ADMIN);
			
			uow.getCredentialsWriteRepo().add(creds);
			uow.getAdminWriteRepo().add(adminInDatabase);
			
			return DatabaseErrors.NO_ERROR;
		}
		
		return DatabaseErrors.ALREADY_EXISTS;
	}
	
	public Admin getAdmin(String username) {
		return uow.getAdminReadRepo().getById(username);
	}
}
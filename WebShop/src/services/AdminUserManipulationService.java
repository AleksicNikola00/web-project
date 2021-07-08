package services;

import beans.enumerations.Role;
import beans.enumerations.TypeOfShopper;
import beans.errors.DatabaseErrors;
import beans.model.Admin;
import beans.model.Credentials;
import beans.model.DeliveryWorker;
import beans.model.Manager;
import beans.model.Shopper;
import dto.NewUser;

public class AdminUserManipulationService extends BaseService {

	public AdminUserManipulationService(String path) {
		super(path);
	}
	
	public String addNewUser(NewUser user) {
		
		Credentials creds = uow.getCredentialsReadRepo().getById(user.getUsername());
		
		if (creds != null) {
			return DatabaseErrors.ALREADY_EXISTS;
		}
		
		creds = new Credentials();
		creds.setUsername(user.getUsername());
		
		creds.setPassword(user.getPassword());
		
		creds.setRole(user.getRole());
		
		
		if (user.getRole().equals(Role.SHOPPER)) {
			Shopper shopper = new Shopper();
			shopper.setName(user.getName());
			shopper.setSurname(user.getSurname());
			shopper.setUsername(user.getUsername());
			shopper.setGender(user.getGender());
			shopper.setDateOfBirth(user.getDateOfBirth());
			shopper.setShopperType(TypeOfShopper.BRONZE);
			shopper.setCollectedPoints(0);
			shopper.setRole(Role.SHOPPER);
			
			uow.getShopperWriteRepo().add(shopper);
		}
		else if (user.getRole().equals(Role.DELIVERY)) {
			DeliveryWorker delivery = new DeliveryWorker();
			delivery.setName(user.getName());
			delivery.setSurname(user.getSurname());
			delivery.setUsername(user.getUsername());
			delivery.setGender(user.getGender());
			delivery.setDateOfBirth(user.getDateOfBirth());
			delivery.setRole(Role.DELIVERY);
			
			uow.getDeliveryWorkerWriteRepo().add(delivery);
		}
		else if (user.getRole().equals(Role.MANAGER)) {
			
			Manager manager = new Manager();
			manager.setName(user.getName());
			manager.setSurname(user.getSurname());
			manager.setUsername(user.getUsername());
			manager.setGender(user.getGender());
			manager.setDateOfBirth(user.getDateOfBirth());
			manager.setRole(Role.MANAGER);
			
			uow.getManagerWriteRepo().add(manager);
		}
		else {
			return DatabaseErrors.ACTION_NOT_ALLOWED;
		}
		
		uow.getCredentialsWriteRepo().add(creds);
		
		return DatabaseErrors.NO_ERROR;
	}
	
	public String editUser(NewUser user) {
		
		Credentials creds = uow.getCredentialsReadRepo().getById(user.getUsername());
		
		if (creds == null) {
			return DatabaseErrors.NOT_FOUND;
		}
		
		if (user.getPassword() != null && !user.getPassword().equals("")) {
			creds.setPassword(user.getPassword());
		}
		
		uow.getCredentialsWriteRepo().update(creds);
		
		if (user.getRole().equals(Role.SHOPPER)) {
			Shopper shopper = uow.getShopperReadRepo().getById(user.getUsername());
			
			shopper.setName(user.getName());
			shopper.setSurname(user.getSurname());
			shopper.setDateOfBirth(user.getDateOfBirth());
			shopper.setGender(user.getGender());
			
			uow.getShopperWriteRepo().update(shopper);
		}
		else if (user.getRole().equals(Role.DELIVERY)) {
			DeliveryWorker delivery = uow.getDeliveryWorkerReadRepo().getById(user.getUsername());
			
			delivery.setName(user.getName());
			delivery.setSurname(user.getSurname());
			delivery.setGender(user.getGender());
			delivery.setDateOfBirth(user.getDateOfBirth());
			
			uow.getDeliveryWorkerWriteRepo().update(delivery);
		}
		else if (user.getRole().equals(Role.MANAGER)) {
			Manager manager = uow.getManagerReadRepo().getById(user.getUsername());
			
			manager.setName(user.getName());
			manager.setSurname(user.getSurname());
			manager.setGender(user.getGender());
			manager.setDateOfBirth(user.getDateOfBirth());
			
			uow.getManagerWriteRepo().update(manager);
		}
		else {
			return DatabaseErrors.ACTION_NOT_ALLOWED;
		}
		
		return DatabaseErrors.NO_ERROR;
	}
	
	public String editMyAccount(NewUser user) {
		Credentials creds = uow.getCredentialsReadRepo().getById(user.getUsername());
		
		if (creds == null) {
			return DatabaseErrors.NOT_FOUND;
		}
		
		Admin admin = uow.getAdminReadRepo().getById(user.getUsername());
		
		if (user.getPassword() != null && !user.getPassword().equals("")) {
			creds.setPassword(user.getPassword());
		}
		
		admin.setName(user.getName());
		admin.setSurname(user.getSurname());
		admin.setGender(user.getGender());
		admin.setDateOfBirth(user.getDateOfBirth());
		
		uow.getCredentialsWriteRepo().update(creds);
		uow.getAdminWriteRepo().update(admin);
		
		return DatabaseErrors.NO_ERROR;
	}
}

package services;

import beans.errors.DatabaseErrors;
import beans.model.Credentials;
import beans.model.DeliveryWorker;
import beans.model.Manager;
import beans.model.Shopper;
import dto.LoggedInUser;
import dto.NewUser;

public class UserService extends BaseService {
	
	
	public UserService(String path) {
		super(path);
	}
	
	public LoggedInUser updateShopperInfo(NewUser shopper) {
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
	
	public LoggedInUser updateManager(NewUser manager) {
		Manager managerInDataBase = uow.getManagerReadRepo().getById(manager.getUsername());
		Credentials credsInDatabase = uow.getCredentialsReadRepo().getById(manager.getUsername());
		if (managerInDataBase == null || credsInDatabase == null) {
			return null;
		}
		managerInDataBase.setName(manager.getName());
		managerInDataBase.setSurname(manager.getSurname());
		managerInDataBase.setGender(manager.getGender());
		managerInDataBase.setDateOfBirth(manager.getDateOfBirth());
		
		uow.getManagerWriteRepo().update(managerInDataBase);
		
		if(!manager.getPassword().isEmpty()) {
			credsInDatabase.setPassword(manager.getPassword());
			uow.getCredentialsWriteRepo().update(credsInDatabase);
		}
		LoggedInUser ret = generateLoggedInManager(credsInDatabase);
		return ret;
	}
	
	private LoggedInUser generateLoggedInManager(Credentials creds) {
		Manager manager = uow.getManagerReadRepo().getById(creds.getUsername());
		
		LoggedInUser retWorker = new LoggedInUser();
		retWorker.setFirstname(manager.getName());
		retWorker.setLastname(manager.getSurname());
		retWorker.setUsername(manager.getUsername());
		retWorker.setDateOfBirth(manager.getDateOfBirth().getDate() + "-" + 
					(manager.getDateOfBirth().getMonth() + 1) + "-" +
					(manager.getDateOfBirth().getYear() + 1900));
		retWorker.setPoints("");
		retWorker.setPassword(creds.getPassword());
		retWorker.setGender(manager.getGender());
		retWorker.setRole(manager.getRole());
		retWorker.setShopperType(null);
		return retWorker;
	}
	
	public LoggedInUser updateDeliveryWorker(NewUser worker) {
		DeliveryWorker workerInDataBase = uow.getDeliveryWorkerReadRepo().getById(worker.getUsername());
		Credentials credsInDatabase = uow.getCredentialsReadRepo().getById(worker.getUsername());
		if (workerInDataBase == null || credsInDatabase == null) {
			return null;
		}
		workerInDataBase.setName(worker.getName());
		workerInDataBase.setSurname(worker.getSurname());
		workerInDataBase.setGender(worker.getGender());
		workerInDataBase.setDateOfBirth(worker.getDateOfBirth());
		
		uow.getDeliveryWorkerWriteRepo().update(workerInDataBase);
		
		if(!worker.getPassword().isEmpty()) {
			credsInDatabase.setPassword(worker.getPassword());
			uow.getCredentialsWriteRepo().update(credsInDatabase);
		}
		LoggedInUser ret = generateLoggedInDeliveryWorker(credsInDatabase);
		return ret;
	}
	
	private LoggedInUser generateLoggedInDeliveryWorker(Credentials creds) {
		DeliveryWorker worker = uow.getDeliveryWorkerReadRepo().getById(creds.getUsername());
		
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
	
	public LoggedInUser generateUserData(String username) {
		return generateLoggedInShopper(uow.getCredentialsReadRepo().getById(username));
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

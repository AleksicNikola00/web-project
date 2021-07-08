package services;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import beans.enumerations.OrderStatus;
import beans.errors.DatabaseErrors;
import beans.model.Admin;
import beans.model.DeliveryWorker;
import beans.model.Manager;
import beans.model.Order;
import beans.model.Restaurant;
import beans.model.Shopper;
import dto.AdminDTO;
import dto.DeliveryWorkerDTO;
import dto.ManagerDTO;
import dto.ShopperDTO;
import repository.DatabaseConstants;

public class AdminUserService extends BaseService {

	public AdminUserService(String path) {
		super(path);
	}
	
	public ArrayList<ShopperDTO> getShoppers(){
		
		ArrayList<Shopper> shoppers = uow.getShopperReadRepo().getAll();
		
		ArrayList<ShopperDTO> ret = new ArrayList<ShopperDTO>();
		
		if(shoppers == null) {
			return ret;
		}
		
		for(Shopper shopper : shoppers) {
			ShopperDTO curr = new ShopperDTO();
			
			curr.setFirstname(shopper.getName());
			curr.setLastname(shopper.getSurname());
			curr.setUsername(shopper.getUsername());
			curr.setDateOfBirth(buildDate(shopper.getDateOfBirth()));
			curr.setGender(shopper.getGender());
			curr.setCollectedPoints(shopper.getCollectedPoints());
			curr.setType(shopper.getShopperType());
			if (shopper.isBlocked()) {
				curr.setStatus("blocked");
			}
			else if (checkIfSuspicious(shopper.getUsername())) {
				curr.setStatus("suspicious");
			}
			else {
				curr.setStatus("normal");
			}
			
			ret.add(curr);
		}
		
		return ret;
	}
	
	public ArrayList<ManagerDTO> getManagers() {
		ArrayList<ManagerDTO> ret = new ArrayList<ManagerDTO>();
		
		ArrayList<Manager> managers = uow.getManagerReadRepo().getAll();
		
		if (managers == null) {
			return ret;
		}
		
		for(Manager manager : managers) {
			ManagerDTO curr = new ManagerDTO();
			
			curr.setFirstname(manager.getName());
			curr.setLastname(manager.getSurname());
			curr.setUsername(manager.getUsername());
			curr.setDateOfBirth(buildDate(manager.getDateOfBirth()));
			curr.setGender(manager.getGender());
			
			Restaurant rest = uow.getRestaurantReadRepo().getRestaurantByManagerUsername(curr.getUsername());
			
			if(rest != null) {
				curr.setRestaurant(rest.getName());
				File image = new File(uow.getDatabasePath() + DatabaseConstants.RESTAURANTS_LOGO_PATH + rest.getId() + ".png");	
				curr.setLogoPath(DatabaseConstants.encodeBase64(image));
			}
			else {
				curr.setRestaurant("");
			}
			
			ret.add(curr);
		}
		
		return ret;
	}
	
	public ArrayList<DeliveryWorkerDTO> getDeliveryWorkers() {
		ArrayList<DeliveryWorkerDTO> ret = new ArrayList<DeliveryWorkerDTO>();
		
		ArrayList<DeliveryWorker> deliveryWorkers = uow.getDeliveryWorkerReadRepo().getAll();
		
		if (deliveryWorkers == null) {
			return ret;
		}
		
		for (DeliveryWorker deliveryWorker : deliveryWorkers) {
			DeliveryWorkerDTO curr = new DeliveryWorkerDTO();
			
			curr.setFirstname(deliveryWorker.getName());
			curr.setLastname(deliveryWorker.getSurname());
			curr.setUsername(deliveryWorker.getUsername());
			
			curr.setDateOfBirth(buildDate(deliveryWorker.getDateOfBirth()));
			curr.setGender(deliveryWorker.getGender());
			
			ret.add(curr);
		}
		
		return ret;
	}
	
	public ArrayList<AdminDTO> getAdmins() {
		ArrayList<AdminDTO> ret = new ArrayList<AdminDTO>();
		
		ArrayList<Admin> admins = uow.getAdminReadRepo().getAll();
		
		if (admins == null) {
			return ret;
		}
		
		for(Admin admin : admins) {
			AdminDTO curr = new AdminDTO();
			
			curr.setFirstname(admin.getName());
			curr.setLastname(admin.getSurname());
			curr.setUsername(admin.getUsername());
			curr.setDateOfBirth(buildDate(admin.getDateOfBirth()));
			curr.setGender(admin.getGender());
			
			ret.add(curr);
		}
		
		return ret;
	}
	
	public String blockUser(String username) {
		if (checkIfSuspicious(username)) {
			Shopper shopper = uow.getShopperReadRepo().getById(username);
			shopper.setBlocked(true);
			
			uow.getShopperWriteRepo().update(shopper);
			
			return DatabaseErrors.NO_ERROR;
		}
		else {
			return DatabaseErrors.FAILED;
		}
	}
	
	public String unblockUser(String username) {
		Shopper shopper = uow.getShopperReadRepo().getById(username);
		
		if (shopper == null) {
			return DatabaseErrors.NOT_FOUND;
		}
		
		if (shopper.isBlocked()) {
			shopper.setBlocked(false);
			
			uow.getShopperWriteRepo().update(shopper);
			return DatabaseErrors.NO_ERROR;
		}
		else {
			return DatabaseErrors.FAILED;
		}
	}
	
	private boolean checkIfSuspicious(String username) {
		
		ArrayList<Order> orders = uow.getOrderReadRepo().getAll();
		int countCanceled = 0;
		
		for(Order o : orders) {
			if (o.isDeleted() || !o.getUsername().equals(username)) {
				continue;
			}
			
			if (o.getStatus().equals(OrderStatus.CANCELED)) {
				Date date = new Date();
				if (date.getMonth() != 0) {
					date.setMonth(date.getMonth() - 1);
				}
				else {
					date.setMonth(11);
					date.setYear(date.getYear() - 1);
				}
				
				if (o.getDate().after(date)) {
					countCanceled++;
					if (countCanceled > 5) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	@SuppressWarnings("deprecation")
	private String buildDate(Date date) {
		return (date.getMonth() + 1) + "-" + date.getDate() + "-" + (date.getYear() + 1900);
	}
}

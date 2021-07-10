package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import beans.model.Manager;
import beans.model.Order;
import beans.model.Shopper;
import dto.ShopperDTO;

public class ManagerService extends BaseService {

	public ManagerService(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	
	public Manager getManager(String username) {
		return uow.getManagerReadRepo().getById(username);
	}
	
	public ArrayList<ShopperDTO> getShoppers(UUID restaurantID){
		ArrayList<ShopperDTO> retShoppers = new ArrayList<>();
		
		for(Order order : uow.getOrderReadRepo().getAll()) {
			if(order.isDeleted()) continue;
			
			if(order.getRestaurant().equals(restaurantID)) 
			{
				ShopperDTO curr = new ShopperDTO();
				Shopper shopper = uow.getShopperReadRepo().getById(order.getUsername());
				curr.setFirstname(shopper.getName());
				curr.setLastname(shopper.getSurname());
				curr.setUsername(shopper.getUsername());
				curr.setDateOfBirth(buildDate(shopper.getDateOfBirth()));
				curr.setGender(shopper.getGender());
				curr.setCollectedPoints(shopper.getCollectedPoints());
				curr.setType(shopper.getShopperType());
				curr.setStatus(null);
				if(!alreadyExists(curr, retShoppers))
					retShoppers.add(curr);
			}
			
		}
		System.out.println("Nadjeno je:" +retShoppers.size()+"za restoran:"+restaurantID);
		return retShoppers;
	}
	
	private boolean alreadyExists(ShopperDTO shopper,ArrayList<ShopperDTO> shoppers) {
		boolean exists = false;
		
		for(ShopperDTO s : shoppers) {
			if(shopper.getUsername().equals(s.getUsername())) {
				exists = true;
				break;
			}
		}
		
		return exists;
	}
	
	@SuppressWarnings("deprecation")
	private String buildDate(Date date) {
		return (date.getMonth() + 1) + "-" + date.getDate() + "-" + (date.getYear() + 1900);
	}

}

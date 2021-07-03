package services;

import java.io.File;

import repository.repos.adminRepo.IReadAdminRepo;
import repository.repos.adminRepo.IWriteAdminRepo;
import repository.repos.adminRepo.ReadAdminRepoText;
import repository.repos.adminRepo.WriteAdminRepoText;
import repository.repos.cityRepo.IReadCityRepo;
import repository.repos.cityRepo.IWriteCityRepo;
import repository.repos.cityRepo.ReadCityRepoText;
import repository.repos.cityRepo.WriteCityRepoText;
import repository.repos.commentRepo.IReadCommentRepo;
import repository.repos.commentRepo.IWriteCommentRepo;
import repository.repos.commentRepo.ReadCommentRepoText;
import repository.repos.commentRepo.WriteCommentRepoText;
import repository.repos.credentialsRepo.IReadCredentialsRepo;
import repository.repos.credentialsRepo.IWriteCredentialsRepo;
import repository.repos.credentialsRepo.ReadCredentialsRepoText;
import repository.repos.credentialsRepo.WriteCredentialsRepoText;
import repository.repos.deliveryWorkerRepo.IReadDeliveryWorkerRepo;
import repository.repos.deliveryWorkerRepo.IWriteDeliveryWorkerRepo;
import repository.repos.deliveryWorkerRepo.ReadDeliveryWorkerRepoText;
import repository.repos.deliveryWorkerRepo.WriteDeliveryWorkerRepoText;
import repository.repos.geoLocationRepo.IReadGeoLocationRepo;
import repository.repos.geoLocationRepo.IWriteGeoLocationRepo;
import repository.repos.geoLocationRepo.ReadGeoLocationRepoText;
import repository.repos.geoLocationRepo.WriteGeoLocationRepoText;
import repository.repos.itemRepo.IReadItemRepo;
import repository.repos.itemRepo.IWriteItemRepo;
import repository.repos.itemRepo.ReadItemRepoText;
import repository.repos.itemRepo.WriteItemRepoText;
import repository.repos.managerRepo.IReadManagerRepo;
import repository.repos.managerRepo.IWriteManagerRepo;
import repository.repos.managerRepo.ReadManagerRepoText;
import repository.repos.managerRepo.WriteManagerRepoText;
import repository.repos.orderRepo.IReadOrderRepo;
import repository.repos.orderRepo.IWriteOrderRepo;
import repository.repos.orderRepo.ReadOrderRepoText;
import repository.repos.orderRepo.WriteOrderRepoText;
import repository.repos.restaurantRepo.IReadRestaurantRepo;
import repository.repos.restaurantRepo.IWriteRestaurantRepo;
import repository.repos.restaurantRepo.ReadRestaurantRepoText;
import repository.repos.restaurantRepo.WriteRestaurantRepoText;
import repository.repos.shopperRepo.IReadShopperRepo;
import repository.repos.shopperRepo.IWriteShopperRepo;
import repository.repos.shopperRepo.ReadShopperRepoText;
import repository.repos.shopperRepo.WriteShopperRepoText;
import repository.repos.shopperTypeRepo.IReadShopperTypeRepo;
import repository.repos.shopperTypeRepo.IWriteShopperTypeRepo;
import repository.repos.shopperTypeRepo.ReadShopperTypeRepoText;
import repository.repos.shopperTypeRepo.WriteShopperTypeRepoText;

public final class UnitOfWork {
	private IReadAdminRepo adminReadRepo;
	private IWriteAdminRepo adminWriteRepo;
	private IReadCityRepo cityReadRepo;
	private IWriteCityRepo cityWriteRepo;
	private IReadCredentialsRepo credentialsReadRepo;
	private IWriteCredentialsRepo credentialsWriteRepo;
	private IReadDeliveryWorkerRepo deliveryWorkerReadRepo;
	private IWriteDeliveryWorkerRepo deliveryWorkerWriteRepo;
	private IReadGeoLocationRepo geoLocationReadRepo;
	private IWriteGeoLocationRepo geoLocationWriteRepo;
	private IReadItemRepo itemReadRepo;
	private IWriteItemRepo itemWriteRepo;
	private IReadManagerRepo managerReadRepo;
	private IWriteManagerRepo managerWriteRepo;
	private IReadOrderRepo orderReadRepo;
	private IWriteOrderRepo orderWriteRepo;
	private IReadRestaurantRepo restaurantReadRepo;
	private IWriteRestaurantRepo restaurantWriteRepo;
	private IReadShopperRepo shopperReadRepo;
	private IWriteShopperRepo shopperWriteRepo;
	private IReadShopperTypeRepo shopperTypeReadRepo;
	private IWriteShopperTypeRepo shopperTypeWriteRepo;
	private IReadCommentRepo commentReadRepo;
	private IWriteCommentRepo commentWriteRepo;
	
	private String path;

	public UnitOfWork(String path) {
		ResolvePath(path);
		InitializeTextRepos();
	}

	public String getDatabasePath() {
		return path;
	}
	
	public IReadCommentRepo getCommentReadRepo() {
		return commentReadRepo;
	}
	
	public IWriteCommentRepo getCommentWriteRepo() {
		return commentWriteRepo;
	}
	
	public IReadAdminRepo getAdminReadRepo() {
		return adminReadRepo;
	}

	public IWriteAdminRepo getAdminWriteRepo() {
		return adminWriteRepo;
	}

	public IReadCityRepo getCityReadRepo() {
		return cityReadRepo;
	}

	public IWriteCityRepo getCityWriteRepo() {
		return cityWriteRepo;
	}

	public IReadCredentialsRepo getCredentialsReadRepo() {
		return credentialsReadRepo;
	}

	public IWriteCredentialsRepo getCredentialsWriteRepo() {
		return credentialsWriteRepo;
	}

	public IReadDeliveryWorkerRepo getDeliveryWorkerReadRepo() {
		return deliveryWorkerReadRepo;
	}

	public IWriteDeliveryWorkerRepo getDeliveryWorkerWriteRepo() {
		return deliveryWorkerWriteRepo;
	}

	public IReadGeoLocationRepo getGeoLocationReadRepo() {
		return geoLocationReadRepo;
	}

	public IWriteGeoLocationRepo getGeoLocationWriteRepo() {
		return geoLocationWriteRepo;
	}

	public IReadItemRepo getItemReadRepo() {
		return itemReadRepo;
	}

	public IWriteItemRepo getItemWriteRepo() {
		return itemWriteRepo;
	}

	public IReadManagerRepo getManagerReadRepo() {
		return managerReadRepo;
	}

	public IWriteManagerRepo getManagerWriteRepo() {
		return managerWriteRepo;
	}

	public IReadOrderRepo getOrderReadRepo() {
		return orderReadRepo;
	}

	public IWriteOrderRepo getOrderWriteRepo() {
		return orderWriteRepo;
	}

	public IReadRestaurantRepo getRestaurantReadRepo() {
		return restaurantReadRepo;
	}

	public IWriteRestaurantRepo getRestaurantWriteRepo() {
		return restaurantWriteRepo;
	}

	public IReadShopperRepo getShopperReadRepo() {
		return shopperReadRepo;
	}

	public IWriteShopperRepo getShopperWriteRepo() {
		return shopperWriteRepo;
	}

	public IReadShopperTypeRepo getShopperTypeReadRepo() {
		return shopperTypeReadRepo;
	}

	public IWriteShopperTypeRepo getShopperTypeWriteRepo() {
		return shopperTypeWriteRepo;
	}	

	private void InitializeTextRepos() {
		adminReadRepo = new ReadAdminRepoText(path);
		adminWriteRepo = new WriteAdminRepoText(path);
		cityReadRepo = new ReadCityRepoText(path);
		cityWriteRepo = new WriteCityRepoText(path);
		commentReadRepo = new ReadCommentRepoText(path);
		commentWriteRepo = new WriteCommentRepoText(path);
		credentialsReadRepo = new ReadCredentialsRepoText(path);
		credentialsWriteRepo = new WriteCredentialsRepoText(path);
		deliveryWorkerReadRepo = new ReadDeliveryWorkerRepoText(path);
		deliveryWorkerWriteRepo = new WriteDeliveryWorkerRepoText(path);
		geoLocationReadRepo = new ReadGeoLocationRepoText(path);
		geoLocationWriteRepo = new WriteGeoLocationRepoText(path);
		itemReadRepo = new ReadItemRepoText(path);
		itemWriteRepo = new WriteItemRepoText(path);
		managerReadRepo = new ReadManagerRepoText(path);
		managerWriteRepo = new WriteManagerRepoText(path);
		orderReadRepo = new ReadOrderRepoText(path);
		orderWriteRepo = new WriteOrderRepoText(path);
		restaurantReadRepo = new ReadRestaurantRepoText(path);
		restaurantWriteRepo = new WriteRestaurantRepoText(path);
		shopperReadRepo = new ReadShopperRepoText(path);
		shopperWriteRepo = new WriteShopperRepoText(path);
		shopperTypeReadRepo = new ReadShopperTypeRepoText(path);
		shopperTypeWriteRepo = new WriteShopperTypeRepoText(path);
	}
	
	private void ResolvePath(String path) {
		this.path = path.split(".metadata")[0] + "WebProjekat" + File.separator + "DataBase";
	}

}
package services;

import repository.IRepository;
import repository.repos.adminRepo.IReadAdminRepo;
import repository.repos.adminRepo.IWriteAdminRepo;
import repository.repos.cityRepo.IReadCityRepo;
import repository.repos.cityRepo.IWriteCityRepo;
import repository.repos.credentialsRepo.IReadCredentialsRepo;
import repository.repos.credentialsRepo.IWriteCredentialsRepo;
import repository.repos.deliveryWorkerRepo.IReadDeliveryWorkerRepo;
import repository.repos.deliveryWorkerRepo.IWriteDeliveryWorkerRepo;
import repository.repos.geoLocationRepo.IReadGeoLocationRepo;
import repository.repos.geoLocationRepo.IWriteGeoLocationRepo;
import repository.repos.itemRepo.IReadItemRepo;
import repository.repos.itemRepo.IWriteItemRepo;
import repository.repos.managerRepo.IReadManagerRepo;
import repository.repos.managerRepo.IWriteManagerRepo;
import repository.repos.orderRepo.IReadOrderRepo;
import repository.repos.orderRepo.IWriteOrderRepo;
import repository.repos.restaurantRepo.IReadRestaurantRepo;
import repository.repos.restaurantRepo.IWriteRestaurantRepo;
import repository.repos.shopperRepo.IReadShopperRepo;
import repository.repos.shopperRepo.IWriteShopperRepo;
import repository.repos.shopperTypeRepo.IReadShopperTypeRepo;
import repository.repos.shopperTypeRepo.IWriteShopperTypeRepo;

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
   
   public UnitOfWork() {
	   
   }

}
package services;

import repository.repos.deliveryWorkerRepo.IReadDeliveryWorkerRepo;
import repository.repos.deliveryWorkerRepo.IWriteDeliveryWorkerRepo;
import repository.IRepository;
import java.util.*;

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
   
   public IRepository getRepository() {
      // TODO: implement
      return null;
   }

}
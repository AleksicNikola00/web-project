package services;

import beans.model.DeliveryWorker;
import repository.repos.deliveryWorkerRepo.IReadDeliveryWorkerRepo;

public class DeliveryService extends BaseService {
	
	public DeliveryService(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	
	public DeliveryWorker getDeliveryWorker(String username) {
		return uow.getDeliveryWorkerReadRepo().getById(username);
	}

}

package services;

import beans.model.DeliveryWorker;


public class DeliveryService extends BaseService {
	
	public DeliveryService(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	
	public DeliveryWorker getDeliveryWorker(String username) {
		return uow.getDeliveryWorkerReadRepo().getById(username);
	}

}

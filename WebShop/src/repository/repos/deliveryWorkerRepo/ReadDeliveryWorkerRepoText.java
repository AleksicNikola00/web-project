package repository.repos.deliveryWorkerRepo;

import java.util.ArrayList;

import beans.model.DeliveryWorker;

public class ReadDeliveryWorkerRepoText implements IReadDeliveryWorkerRepo {
	private static String path;

	public ReadDeliveryWorkerRepoText(String path) {
		this.path = path;
	}

	@Override
	public DeliveryWorker getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DeliveryWorker> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
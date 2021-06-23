package repository.repos.orderRepo;

import java.util.ArrayList;
import java.util.UUID;

import beans.model.Order;

public class ReadOrderRepoText implements IReadOrderRepo {
	private static String path;

	public ReadOrderRepoText(String path) {
		this.path = path;
	}

	@Override
	public Order getById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Order> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
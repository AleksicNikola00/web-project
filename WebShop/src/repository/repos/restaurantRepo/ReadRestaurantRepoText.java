package repository.repos.restaurantRepo;

import java.util.ArrayList;
import java.util.UUID;

import beans.model.Restaurant;

public class ReadRestaurantRepoText implements IReadRestaurantRepo {
	private static String path;

	public ReadRestaurantRepoText(String path) {
		this.path = path;
	}

	@Override
	public Restaurant getById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Restaurant> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
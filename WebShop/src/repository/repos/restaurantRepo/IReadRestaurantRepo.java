package repository.repos.restaurantRepo;

import java.util.UUID;

import beans.model.Restaurant;

public interface IReadRestaurantRepo extends repository.IReadRepo<UUID,Restaurant> {
	
	public Restaurant getRestaurantByManagerUsername(String username);
}
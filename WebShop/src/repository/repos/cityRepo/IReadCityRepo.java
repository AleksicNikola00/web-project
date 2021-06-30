package repository.repos.cityRepo;

import java.util.UUID;

import beans.model.City;

public interface IReadCityRepo extends repository.IReadRepo<UUID,City> {
	
	City getCityByName(String name);
}
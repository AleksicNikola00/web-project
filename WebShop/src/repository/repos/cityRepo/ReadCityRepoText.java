package repository.repos.cityRepo;

import java.util.ArrayList;
import java.util.UUID;

import beans.model.City;

public class ReadCityRepoText implements IReadCityRepo {
	private static String path;

	public ReadCityRepoText(String path) {
		this.path = path;
	}
	
	@Override
	public City getById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<City> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
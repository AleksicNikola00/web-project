package services;

import java.util.ArrayList;
import java.util.UUID;

import beans.errors.DatabaseErrors;
import beans.model.Admin;
import beans.model.City;

public class CityService extends BaseService {
	
	public CityService(String path) {
		super(path);
	}
	
	public String add(City value) {
		return DatabaseErrors.NO_ERROR;
	}
	
	public String update(City value) {
		return DatabaseErrors.NO_ERROR;
	}
	
	public String delete(City value) {
		return DatabaseErrors.NO_ERROR;
	}
	
	public City getById(UUID id) {
		return uow.getCityReadRepo().getById(id);
	}
	
	public ArrayList<City> getAll(){
		return uow.getCityReadRepo().getAll();
	}
}
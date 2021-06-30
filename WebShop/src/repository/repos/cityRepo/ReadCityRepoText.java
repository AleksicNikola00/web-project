package repository.repos.cityRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.model.Admin;
import beans.model.City;

public class ReadCityRepoText implements IReadCityRepo {
	private static String path;

	public ReadCityRepoText(String path) {
		this.path = path + File.separator + "cities.txt";
	}
	
	@Override
	public City getById(UUID id) {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<City> cities = objectMapper.readValue(readString, new TypeReference<ArrayList<City>>(){});
			
			for(City c : cities) {
				if (c.getId().equals(id)) {
					return c;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

	@Override
	public ArrayList<City> getAll() {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<City> cities = objectMapper.readValue(readString, new TypeReference<ArrayList<City>>(){});
			
			return cities;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

	@Override
	public City getCityByName(String name) {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<City> cities = objectMapper.readValue(readString, new TypeReference<ArrayList<City>>(){});
			
			for(City c : cities) {
				if (c.getName().equalsIgnoreCase(name)) {
					return c;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

}
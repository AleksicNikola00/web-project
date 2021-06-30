package repository.repos.restaurantRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.model.Restaurant;

public class ReadRestaurantRepoText implements IReadRestaurantRepo {
	private static String path;

	public ReadRestaurantRepoText(String path) {
		this.path = path + File.separator + "restaurants.txt";
	}

	@Override
	public Restaurant getById(UUID id) {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Restaurant> restaurants = objectMapper.readValue(readString, new TypeReference<ArrayList<Restaurant>>(){});
			
			for(Restaurant r : restaurants) {
				if (r.getId().equals(id)) {
					return r;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

	@Override
	public ArrayList<Restaurant> getAll() {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Restaurant> restaurants = objectMapper.readValue(readString, new TypeReference<ArrayList<Restaurant>>(){});
			
			return restaurants;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

}
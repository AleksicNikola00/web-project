package repository.repos.restaurantRepo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import beans.model.Restaurant;

public class WriteRestaurantRepoText implements IWriteRestaurantRepo {
	private static String path;

	public WriteRestaurantRepoText(String path) {
		this.path = path + File.separator + "restaurants.txt";
	}

	@Override
	public void add(Restaurant value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Restaurant> restaurantData = mapper.readValue(data, new TypeReference<ArrayList<Restaurant>>(){});
			restaurantData.add(value);
			
			String json = mapper.writeValueAsString(restaurantData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully appended to Restaurants");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Restaurants to json did not work");
		} catch (IOException e) {
			System.out.println("Something went wrong with files...\nPath: " + path);
		} finally {
			try {
				pw.close();
				bw.close();
				fw.close();
			} catch (IOException io) {}
		}

	}

	@Override
	public void update(Restaurant value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Restaurant> restaurantData = mapper.readValue(data, new TypeReference<ArrayList<Restaurant>>(){});
			
			for (int i = 0; i < restaurantData.size(); i++) {
				if (restaurantData.get(i).getId().equals(value.getId())) {
					restaurantData.set(i, value);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(restaurantData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully edited Restaurants\nEdited restaurant id : " + value.getId());
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Restaurants to json did not work");
		} catch (IOException e) {
			System.out.println("Something went wrong with files...\nPath: " + path);
		} finally {
			try {
				pw.close();
				bw.close();
				fw.close();
			} catch (IOException io) {}
		}

	}

	@Override
	public void delete(Restaurant value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Restaurant> restaurantData = mapper.readValue(data, new TypeReference<ArrayList<Restaurant>>(){});
			
			for (int i = 0; i < restaurantData.size(); i++) {
				if (restaurantData.get(i).getId().equals(value.getId())) {
					restaurantData.get(i).setDeleted(true);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(restaurantData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully deleted from Restaurants");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Restaurants to json did not work");
		} catch (IOException e) {
			System.out.println("Something went wrong with files...\nPath: " + path);
		} finally {
			try {
				pw.close();
				bw.close();
				fw.close();
			} catch (IOException io) {}
		}

	}

}
package repository.repos.cityRepo;

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

import beans.model.Admin;
import beans.model.City;

public class WriteCityRepoText implements IWriteCityRepo {
	private static String path;

	public WriteCityRepoText(String path) {
		this.path = path + File.separator + "cities.txt";
	}
	
	@Override
	public void add(City value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<City> cityData = mapper.readValue(data, new TypeReference<ArrayList<City>>(){});
			cityData.add(value);
			
			String json = mapper.writeValueAsString(cityData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully appended to Cities");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Cities to json did not work");
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
	public void update(City value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<City> cityData = mapper.readValue(data, new TypeReference<ArrayList<City>>(){});
			
			for (int i = 0; i < cityData.size(); i++) {
				if (cityData.get(i).getId().equals(value.getId())) {
					cityData.set(i, value);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(cityData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully edited Cities\nEdited city id : " + value.getId());
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting City to json did not work");
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
	public void delete(City value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<City> cityData = mapper.readValue(data, new TypeReference<ArrayList<City>>(){});
			
			for (int i = 0; i < cityData.size(); i++) {
				if (cityData.get(i).getId().equals(value.getId())) {
					cityData.get(i).setDeleted(true);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(cityData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully deleted from Cities");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Cities to json did not work");
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
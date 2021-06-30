package repository.repos.geoLocationRepo;

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

import beans.model.GeoLocation;

public class WriteGeoLocationRepoText implements IWriteGeoLocationRepo {
	private static String path;

	public WriteGeoLocationRepoText(String path) {
		this.path = path + File.separator + "geolocations.txt";
	}

	@Override
	public void add(GeoLocation value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<GeoLocation> geoLocationData = mapper.readValue(data, new TypeReference<ArrayList<GeoLocation>>(){});
			geoLocationData.add(value);
			
			String json = mapper.writeValueAsString(geoLocationData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully appended to GeoLocation");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting GeoLocation to json did not work");
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
	public void update(GeoLocation value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<GeoLocation> geoLocationData = mapper.readValue(data, new TypeReference<ArrayList<GeoLocation>>(){});
			
			for (int i = 0; i < geoLocationData.size(); i++) {
				if (geoLocationData.get(i).getId().equals(value.getId())) {
					geoLocationData.set(i, value);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(geoLocationData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully edited GeoLocation\nEdited GeoLocation id : " + value.getId());
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting GeoLocation to json did not work");
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
	public void delete(GeoLocation value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<GeoLocation> geoLocationData = mapper.readValue(data, new TypeReference<ArrayList<GeoLocation>>(){});
			
			for (int i = 0; i < geoLocationData.size(); i++) {
				if (geoLocationData.get(i).getId().equals(value.getId())) {
					geoLocationData.get(i).setDeleted(true);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(geoLocationData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully deleted from GeoLocations");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting GeoLocations to json did not work");
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
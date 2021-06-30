package repository.repos.geoLocationRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.model.GeoLocation;

public class ReadGeoLocationRepoText implements IReadGeoLocationRepo {
	private static String path;

	public ReadGeoLocationRepoText(String path) {
		this.path = path + File.separator + "geolocations.txt";
	}

	@Override
	public GeoLocation getById(UUID id) {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<GeoLocation> geoLocations = objectMapper.readValue(readString, new TypeReference<ArrayList<GeoLocation>>(){});
			
			for(GeoLocation g : geoLocations) {
				if (g.getId().equals(id)) {
					return g;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

	@Override
	public ArrayList<GeoLocation> getAll() {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<GeoLocation> geoLocations = objectMapper.readValue(readString, new TypeReference<ArrayList<GeoLocation>>(){});
			
			return geoLocations;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

}
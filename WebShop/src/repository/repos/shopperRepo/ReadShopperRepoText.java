package repository.repos.shopperRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.model.Credentials;
import beans.model.Shopper;

public class ReadShopperRepoText implements IReadShopperRepo {
	private static String path;

	public ReadShopperRepoText(String path) {
		this.path = path + File.separator + "shoppers.txt";
	}

	@Override
	public Shopper getById(String id) {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Shopper> shoppers = objectMapper.readValue(readString, new TypeReference<ArrayList<Shopper>>(){});
			
			for(Shopper s : shoppers) {
				if (s.getUsername().equals(id) && !s.isDeleted()) {
					return s;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		
		return null;
	}

	@Override
	public ArrayList<Shopper> getAll() {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Shopper> shoppers = objectMapper.readValue(readString, new TypeReference<ArrayList<Shopper>>(){});
			
			return shoppers;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

}
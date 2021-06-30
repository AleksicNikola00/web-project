package repository.repos.shopperTypeRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.enumerations.TypeOfShopper;
import beans.model.ShopperType;

public class ReadShopperTypeRepoText implements IReadShopperTypeRepo {
	private static String path;

	public ReadShopperTypeRepoText(String path) {
		this.path = path + File.separator + "shopperTypes.txt";
	}

	@Override
	public ShopperType getById(TypeOfShopper id) {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<ShopperType> shopperTypes = objectMapper.readValue(readString, new TypeReference<ArrayList<ShopperType>>(){});
			
			for(ShopperType s : shopperTypes) {
				if (s.getType().equals(id) && !s.isDeleted()) {
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
	public ArrayList<ShopperType> getAll() {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<ShopperType> shopperTypes = objectMapper.readValue(readString, new TypeReference<ArrayList<ShopperType>>(){});
			
			return shopperTypes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

}
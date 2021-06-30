package repository.repos.itemRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.model.Item;

public class ReadItemRepoText implements IReadItemRepo {
	private static String path;

	public ReadItemRepoText(String path) {
		this.path = path + File.separator + "items.txt";
	}

	@Override
	public Item getById(UUID id) {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Item> items = objectMapper.readValue(readString, new TypeReference<ArrayList<Item>>(){});
			
			for(Item i : items) {
				if (i.getId().equals(id)) {
					return i;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

	@Override
	public ArrayList<Item> getAll() {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Item> items = objectMapper.readValue(readString, new TypeReference<ArrayList<Item>>(){});
			
			return items;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

}
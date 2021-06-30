package repository.repos.orderRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.model.Order;

public class ReadOrderRepoText implements IReadOrderRepo {
	private static String path;

	public ReadOrderRepoText(String path) {
		this.path = path;
	}

	@Override
	public Order getById(UUID id) {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Order> orders = objectMapper.readValue(readString, new TypeReference<ArrayList<Order>>(){});
			
			for(Order c : orders) {
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
	public ArrayList<Order> getAll() {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Order> orders = objectMapper.readValue(readString, new TypeReference<ArrayList<Order>>(){});
			
			return orders;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

}
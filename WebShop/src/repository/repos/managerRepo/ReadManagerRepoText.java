package repository.repos.managerRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.model.Manager;

public class ReadManagerRepoText implements IReadManagerRepo {
	private static String path;

	public ReadManagerRepoText(String path) {
		this.path = path + File.separator + "managers.txt";
	}

	@Override
	public Manager getById(String id) {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Manager> managers = objectMapper.readValue(readString, new TypeReference<ArrayList<Manager>>(){});
			
			for(Manager m : managers) {
				if (m.getUsername().equals(id)) {
					return m;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

	@Override
	public ArrayList<Manager> getAll() {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Manager> managers = objectMapper.readValue(readString, new TypeReference<ArrayList<Manager>>(){});
			
			return managers;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

}
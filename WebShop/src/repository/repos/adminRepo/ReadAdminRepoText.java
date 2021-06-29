package repository.repos.adminRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.model.Admin;
import beans.model.Credentials;

public class ReadAdminRepoText implements IReadAdminRepo {
	private static String path;

	public ReadAdminRepoText(String path) {
		this.path = path + File.separator + "admins.txt";
	}
	
	@Override
	public Admin getById(String id) {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Admin> admins = objectMapper.readValue(readString, new TypeReference<ArrayList<Admin>>(){});
			
			for(Admin a : admins) {
				if (a.getUsername().equals(id)) {
					return a;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

	@Override
	public ArrayList<Admin> getAll() {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Admin> admins = objectMapper.readValue(readString, new TypeReference<ArrayList<Admin>>(){});
			
			return admins;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

}
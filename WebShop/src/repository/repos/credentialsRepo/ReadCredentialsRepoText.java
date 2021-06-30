package repository.repos.credentialsRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.model.Credentials;

public class ReadCredentialsRepoText implements IReadCredentialsRepo {
	private static String path;

	public ReadCredentialsRepoText(String path) {
		this.path = path + File.separator + "credentials.txt";
	}

	@Override
	public Credentials getById(String id) {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Credentials> credentials = objectMapper.readValue(readString, new TypeReference<ArrayList<Credentials>>(){});
			
			for(Credentials c : credentials) {
				if (c.getUsername().equals(id)) {
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
	public ArrayList<Credentials> getAll() {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Credentials> credentials = objectMapper.readValue(readString, new TypeReference<ArrayList<Credentials>>(){});
			
			return credentials;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

}
package repository.repos.credentialsRepo;

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
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import beans.model.Credentials;

public class WriteCredentialsRepoText implements IWriteCredentialsRepo {
	private static String path;

	public WriteCredentialsRepoText(String path) {
		this.path = path + File.separator + "credentials.txt";
	}
	
	@Override
	public void add(Credentials value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Credentials> credentialsData = mapper.readValue(data, new TypeReference<ArrayList<Credentials>>(){});
			credentialsData.add(value);
			
			String json = mapper.writeValueAsString(credentialsData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully appended to Credentials");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Credentials to json did not work");
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
	public void update(Credentials value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Credentials> credentialsData = mapper.readValue(data, new TypeReference<ArrayList<Credentials>>(){});
			
			for (int i = 0; i < credentialsData.size(); i++) {
				if (credentialsData.get(i).getUsername().equals(value.getUsername())) {
					credentialsData.set(i, value);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(credentialsData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully edited Credentials\nEdited username : " + value.getUsername());
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Credentials to json did not work");
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
	public void delete(Credentials value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Credentials> credentialsData = mapper.readValue(data, new TypeReference<ArrayList<Credentials>>(){});
			
			for (int i = 0; i < credentialsData.size(); i++) {
				if (credentialsData.get(i).getUsername().equals(value.getUsername())) {
					credentialsData.get(i).setDeleted(true);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(credentialsData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully deleted from Credentials");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Credentials to json did not work");
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
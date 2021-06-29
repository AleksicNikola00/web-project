package repository.repos.adminRepo;

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

import beans.model.Admin;
import beans.model.Credentials;

public class WriteAdminRepoText implements IWriteAdminRepo {
	private static String path;

	public WriteAdminRepoText(String path) {
		this.path = path + File.separator + "admins.txt";
	}
	
	@Override
	public void add(Admin value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Admin> adminData = mapper.readValue(data, new TypeReference<ArrayList<Admin>>(){});
			adminData.add(value);
			
			String json = mapper.writeValueAsString(adminData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully appended to Admins");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Admins to json did not work");
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
	public void update(Admin value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Admin> adminData = mapper.readValue(data, new TypeReference<ArrayList<Admin>>(){});
			
			for (int i = 0; i < adminData.size(); i++) {
				if (adminData.get(i).getUsername().equals(value.getUsername())) {
					adminData.set(i, value);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(adminData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully edited Admins\nEdited username : " + value.getUsername());
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Admins to json did not work");
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
	public void delete(Admin value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Admin> adminData = mapper.readValue(data, new TypeReference<ArrayList<Admin>>(){});
			
			for (int i = 0; i < adminData.size(); i++) {
				if (adminData.get(i).getUsername().equals(value.getUsername())) {
					adminData.get(i).setDeleted(true);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(adminData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully appended to Admins");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Admins to json did not work");
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
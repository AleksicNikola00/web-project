package repository.repos.managerRepo;

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

import beans.model.Manager;

public class WriteManagerRepoText implements IWriteManagerRepo {
	private static String path;

	public WriteManagerRepoText(String path) {
		this.path = path + File.separator + "managers.txt";
	}

	@Override
	public void add(Manager value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Manager> managerData = mapper.readValue(data, new TypeReference<ArrayList<Manager>>(){});
			managerData.add(value);
			
			String json = mapper.writeValueAsString(managerData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully appended to Managers");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Managers to json did not work");
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
	public void update(Manager value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Manager> managerData = mapper.readValue(data, new TypeReference<ArrayList<Manager>>(){});
			
			for (int i = 0; i < managerData.size(); i++) {
				if (managerData.get(i).getUsername().equals(value.getUsername())) {
					managerData.set(i, value);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(managerData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully edited Managers\nEdited username : " + value.getUsername());
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Managers to json did not work");
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
	public void delete(Manager value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Manager> managerData = mapper.readValue(data, new TypeReference<ArrayList<Manager>>(){});
			
			for (int i = 0; i < managerData.size(); i++) {
				if (managerData.get(i).getUsername().equals(value.getUsername())) {
					managerData.get(i).setDeleted(true);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(managerData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully deleted from Managers");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Managers to json did not work");
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
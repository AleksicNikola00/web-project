package repository.repos.itemRepo;

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

import beans.model.Item;

public class WriteItemRepoText implements IWriteItemRepo {
	private static String path;

	public WriteItemRepoText(String path) {
		this.path = path + File.separator + "items.txt";
	}

	@Override
	public void add(Item value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Item> itemData = mapper.readValue(data, new TypeReference<ArrayList<Item>>(){});
			itemData.add(value);
			
			String json = mapper.writeValueAsString(itemData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully appended to Items");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Items to json did not work");
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
	public void update(Item value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Item> itemData = mapper.readValue(data, new TypeReference<ArrayList<Item>>(){});
			
			for (int i = 0; i < itemData.size(); i++) {
				if (itemData.get(i).getId().equals(value.getId())) {
					itemData.set(i, value);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(itemData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully edited Items\nEdited item id : " + value.getId());
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Items to json did not work");
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
	public void delete(Item value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Item> itemData = mapper.readValue(data, new TypeReference<ArrayList<Item>>(){});
			
			for (int i = 0; i < itemData.size(); i++) {
				if (itemData.get(i).getId().equals(value.getId())) {
					itemData.get(i).setDeleted(true);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(itemData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully deleted from Items");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Items to json did not work");
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
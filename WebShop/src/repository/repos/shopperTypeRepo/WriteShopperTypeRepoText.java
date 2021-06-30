package repository.repos.shopperTypeRepo;

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

import beans.model.ShopperType;

public class WriteShopperTypeRepoText implements IWriteShopperTypeRepo {
	private static String path;

	public WriteShopperTypeRepoText(String path) {
		this.path = path + File.separator + "shopperTypes.txt";
	}

	@Override
	public void add(ShopperType value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<ShopperType> shopperTypeData = mapper.readValue(data, new TypeReference<ArrayList<ShopperType>>(){});
			shopperTypeData.add(value);
			
			String json = mapper.writeValueAsString(shopperTypeData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully appended to ShopperType");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting ShopperType to json did not work");
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
	public void update(ShopperType value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<ShopperType> shopperTypeData = mapper.readValue(data, new TypeReference<ArrayList<ShopperType>>(){});
			
			for (int i = 0; i < shopperTypeData.size(); i++) {
				if (shopperTypeData.get(i).getType().equals(value.getType())) {
					shopperTypeData.set(i, value);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(shopperTypeData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully edited ShopperType\nEdited shopperType : " + value.getType());
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting ShopperType to json did not work");
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
	public void delete(ShopperType value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<ShopperType> shopperTypeData = mapper.readValue(data, new TypeReference<ArrayList<ShopperType>>(){});
			
			for (int i = 0; i < shopperTypeData.size(); i++) {
				if (shopperTypeData.get(i).getType().equals(value.getType())) {
					shopperTypeData.get(i).setDeleted(true);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(shopperTypeData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully deleted from ShopperTypes");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting ShopperTypes to json did not work");
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
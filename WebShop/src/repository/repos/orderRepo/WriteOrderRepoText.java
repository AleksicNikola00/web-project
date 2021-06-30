package repository.repos.orderRepo;

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

import beans.model.Order;

public class WriteOrderRepoText implements IWriteOrderRepo {
	private static String path;

	public WriteOrderRepoText(String path) {
		this.path = path + File.separator + "orders.txt";
	}

	@Override
	public void add(Order value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Order> orderData = mapper.readValue(data, new TypeReference<ArrayList<Order>>(){});
			orderData.add(value);
			
			String json = mapper.writeValueAsString(orderData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully appended to Orders");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Orders to json did not work");
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
	public void update(Order value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Order> orderData = mapper.readValue(data, new TypeReference<ArrayList<Order>>(){});
			
			for (int i = 0; i < orderData.size(); i++) {
				if (orderData.get(i).getId().equals(value.getId())) {
					orderData.set(i, value);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(orderData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully edited Orders\nEdited order id : " + value.getId());
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Orders to json did not work");
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
	public void delete(Order value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Order> orderData = mapper.readValue(data, new TypeReference<ArrayList<Order>>(){});
			
			for (int i = 0; i < orderData.size(); i++) {
				if (orderData.get(i).getId().equals(value.getId())) {
					orderData.get(i).setDeleted(true);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(orderData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully deleted from Orders");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Orders to json did not work");
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
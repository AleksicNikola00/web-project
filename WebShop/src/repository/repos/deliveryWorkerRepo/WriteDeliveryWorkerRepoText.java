package repository.repos.deliveryWorkerRepo;

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

import beans.model.DeliveryWorker;

public class WriteDeliveryWorkerRepoText implements IWriteDeliveryWorkerRepo {
	private static String path;

	public WriteDeliveryWorkerRepoText(String path) {
		this.path = path + File.separator + "deliveryWorkers.txt";
	}

	@Override
	public void add(DeliveryWorker value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<DeliveryWorker> deliveryWorkerData = mapper.readValue(data, new TypeReference<ArrayList<DeliveryWorker>>(){});
			deliveryWorkerData.add(value);
			
			String json = mapper.writeValueAsString(deliveryWorkerData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully appended to DeliveryWorker");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting DeliveryWorker to json did not work");
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
	public void update(DeliveryWorker value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<DeliveryWorker> deliveryWorkerData = mapper.readValue(data, new TypeReference<ArrayList<DeliveryWorker>>(){});
			
			for (int i = 0; i < deliveryWorkerData.size(); i++) {
				if (deliveryWorkerData.get(i).getUsername().equals(value.getUsername())) {
					deliveryWorkerData.set(i, value);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(deliveryWorkerData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully edited DeliveryWorkers\nEdited username : " + value.getUsername());
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting DeliveryWorkers to json did not work");
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
	public void delete(DeliveryWorker value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<DeliveryWorker> deliveryWorkerData = mapper.readValue(data, new TypeReference<ArrayList<DeliveryWorker>>(){});
			
			for (int i = 0; i < deliveryWorkerData.size(); i++) {
				if (deliveryWorkerData.get(i).getUsername().equals(value.getUsername())) {
					deliveryWorkerData.get(i).setDeleted(true);
					break;
				}
			}
			
			String json = mapper.writeValueAsString(deliveryWorkerData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully deleted from DeliveryWorkers");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting DeliveryWorkers to json did not work");
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
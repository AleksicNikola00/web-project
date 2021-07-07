package repository.repos.deliveryWorkerRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.model.DeliveryWorker;

public class ReadDeliveryWorkerRepoText implements IReadDeliveryWorkerRepo {
	private static String path;

	public ReadDeliveryWorkerRepoText(String path) {
		this.path = path + File.separator + "deliveryWorkers.txt";
	}

	@Override
	public DeliveryWorker getById(String id) {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<DeliveryWorker> deliveryWorkers = objectMapper.readValue(readString, new TypeReference<ArrayList<DeliveryWorker>>(){});
			
			for(DeliveryWorker d : deliveryWorkers) {
				if (d.getUsername().equals(id)) {
					return d;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

	@Override
	public ArrayList<DeliveryWorker> getAll() {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<DeliveryWorker> deliveryWorkers = objectMapper.readValue(readString, new TypeReference<ArrayList<DeliveryWorker>>(){});
			
			return deliveryWorkers;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

}
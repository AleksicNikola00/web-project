package repository.repos.shopperRepo;

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

import beans.model.Credentials;
import beans.model.Shopper;

public class WriteShopperRepoText implements IWriteShopperRepo {
	private static String path;

	public WriteShopperRepoText(String path) {
		this.path = path + File.separator + "shoppers.txt";
	}

	@Override
	public void add(Shopper value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Shopper> shopperData = mapper.readValue(data, new TypeReference<ArrayList<Shopper>>(){});
			shopperData.add(value);
			
			String json = mapper.writeValueAsString(shopperData);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			pw.println(json);
			System.out.println("Successfully appended to Shoppers");
			pw.flush();
			
		} catch (JsonProcessingException e) {
			System.out.println("Converting Shoppers to json did not work");
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
	public void update(Shopper value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Shopper value) {
		// TODO Auto-generated method stub

	}

}
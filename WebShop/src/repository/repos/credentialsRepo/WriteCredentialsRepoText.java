package repository.repos.credentialsRepo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
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
			String json = mapper.writeValueAsString(value);
			
			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database, true);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Credentials value) {
		// TODO Auto-generated method stub

	}



}
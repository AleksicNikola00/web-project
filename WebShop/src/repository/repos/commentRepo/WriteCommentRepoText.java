package repository.repos.commentRepo;

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

import beans.model.Comment;

public class WriteCommentRepoText implements IWriteCommentRepo {
	private static String path;

	public WriteCommentRepoText(String path) {
		this.path = path + File.separator + "comments.txt";
	}

	@Override
	public void add(Comment value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Comment> commentData = mapper.readValue(data, new TypeReference<ArrayList<Comment>>(){});
			commentData.add(value);

			String json = mapper.writeValueAsString(commentData);

			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			pw.println(json);
			System.out.println("Successfully appended to Comments");
			pw.flush();

		} catch (JsonProcessingException e) {
			System.out.println("Converting Comments to json did not work");
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
	public void update(Comment value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Comment> commentData = mapper.readValue(data, new TypeReference<ArrayList<Comment>>(){});

			for (int i = 0; i < commentData.size(); i++) {
				if (commentData.get(i).getId().equals(value.getId())) {
					commentData.set(i, value);
					break;
				}
			}

			String json = mapper.writeValueAsString(commentData);

			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			pw.println(json);
			System.out.println("Successfully edited Comments\nEdited comment id : " + value.getId());
			pw.flush();

		} catch (JsonProcessingException e) {
			System.out.println("Converting Comments to json did not work");
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
	public void delete(Comment value) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			String data = Files.readString(Paths.get(this.path));
			ArrayList<Comment> commentData = mapper.readValue(data, new TypeReference<ArrayList<Comment>>(){});

			for (int i = 0; i < commentData.size(); i++) {
				if (commentData.get(i).getId().equals(value.getId())) {
					commentData.get(i).setDeleted(true);
					break;
				}
			}

			String json = mapper.writeValueAsString(commentData);

			File database = new File(path);
			System.out.println(database.getAbsolutePath());
			fw = new FileWriter(database);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			pw.println(json);
			System.out.println("Successfully deleted from Comments");
			pw.flush();

		} catch (JsonProcessingException e) {
			System.out.println("Converting Comments to json did not work");
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
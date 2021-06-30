package repository.repos.commentRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.model.Comment;

public class ReadCommentRepoText implements IReadCommentRepo {
   private static String path;

   public ReadCommentRepoText(String path) {
	   this.path = path + File.separator + "comments.txt";
   }
   
	@Override
	public Comment getById(UUID id) {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Comment> comments = objectMapper.readValue(readString, new TypeReference<ArrayList<Comment>>(){});
			
			for(Comment c : comments) {
				if (c.getId().equals(id)) {
					return c;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}
	
	@Override
	public ArrayList<Comment> getAll() {
		try {
			String readString = Files.readString(Paths.get(this.path));
			
			ObjectMapper objectMapper = new ObjectMapper();
			ArrayList<Comment> comments = objectMapper.readValue(readString, new TypeReference<ArrayList<Comment>>(){});
			
			return comments;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from path : " + this.path);
		}
		return null;
	}

}
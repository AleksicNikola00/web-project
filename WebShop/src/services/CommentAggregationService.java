package services;

import java.util.ArrayList;
import java.util.UUID;

import beans.model.Comment;

public class CommentAggregationService extends BaseService {
	
	public CommentAggregationService(String path) {
		super(path);
	}
	
	public ArrayList<Comment> getCommentsForRestaurant(UUID id){
		ArrayList<Comment> allComments = uow.getCommentReadRepo().getAll();
		
		ArrayList<Comment> ret = new ArrayList<Comment>();
		
		for(Comment c : allComments) {
			
			if (c.isDeleted() || !c.isAllowed() || !c.getRestaurantId().equals(id)) {
				continue;
			}
			
			ret.add(c);
		}
		
		return ret;
	}
}

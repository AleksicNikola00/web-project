package services;

import java.util.ArrayList;
import java.util.UUID;

import com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser;

import beans.enumerations.CommentStatus;
import beans.errors.DatabaseErrors;
import beans.model.Comment;
import beans.model.Restaurant;
import beans.model.Shopper;

public class CRUDCommentService extends BaseService {
	
	public CRUDCommentService(String path) {
		super(path);
	}
	
	public String add(Comment comment) {
		
		Restaurant restaurant = uow.getRestaurantReadRepo().getById(comment.getRestaurantId());
		Shopper shopper = uow.getShopperReadRepo().getById(comment.getUsername());
		
		if (restaurant == null || restaurant.isDeleted() ||
				shopper == null || shopper.isDeleted()) {
			return DatabaseErrors.NOT_FOUND;
		}
		
		comment.setId(UUID.randomUUID());
		comment.setStatus(CommentStatus.PENDING);
		uow.getCommentWriteRepo().add(comment);
		
		return DatabaseErrors.NO_ERROR;
	}
	
	public String update(Comment comment) {
		Restaurant restaurant = uow.getRestaurantReadRepo().getById(comment.getRestaurantId());
		if(restaurant == null) return DatabaseErrors.NOT_FOUND;
		
		comment.setStatus(comment.getStatus());
		uow.getCommentWriteRepo().update(comment);
		
		//update ocenu restorana ako je komentar dozvoljen
		if(comment.getStatus().equals(CommentStatus.ALLOWED)) {
			ArrayList<Comment> comments = uow.getCommentReadRepo().getAll();
			int allowedCommentsForSameRestaurant = 0;
			double sum = 0;
			for(Comment c : comments) {
				if(c.getStatus().equals(CommentStatus.ALLOWED) && c.getRestaurantId().equals(comment.getRestaurantId())) {
					allowedCommentsForSameRestaurant++;
					sum+=c.getMark();
				}
					
			}
		
			double rating = sum/allowedCommentsForSameRestaurant;
			restaurant.setRating(rating);
			uow.getRestaurantWriteRepo().update(restaurant);
		}
		
		return DatabaseErrors.NO_ERROR;
	}
}

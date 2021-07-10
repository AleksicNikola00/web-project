package services;

import java.util.UUID;

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
		
		return DatabaseErrors.NO_ERROR;
	}
}

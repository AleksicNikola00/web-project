package services;

import java.util.UUID;

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
		uow.getCommentWriteRepo().add(comment);
		
		return DatabaseErrors.NO_ERROR;
	}
}

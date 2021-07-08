package controllers;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.model.Comment;
import services.CommentAggregationService;

@Path("/getcomments")
public class GetCommentController {
	
	@Context
	ServletContext ctx;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Comment> getCommentForRestaurant(@PathParam("id") UUID id){
		CommentAggregationService service = new CommentAggregationService(ctx.getRealPath(""));
		
		return service.getCommentsForRestaurant(id);
	}
	
	@GET
	@Path("/all/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Comment> getAllCommentForRestaurant(@PathParam("id") UUID id){
		CommentAggregationService service = new CommentAggregationService(ctx.getRealPath(""));
		
		return service.getAllCommentsForRestaurant(id);
	}
	
}

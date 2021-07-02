package controllers;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.model.Comment;
import services.CRUDCommentService;

@Path("/comment")
public class CommentController {

	@Context
	ServletContext ctx;
	
	@POST
	@Path("/submit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String submitComment(Comment comment) {
		CRUDCommentService service = new CRUDCommentService(ctx.getRealPath(""));
		
		return service.add(comment);
	}
	
}

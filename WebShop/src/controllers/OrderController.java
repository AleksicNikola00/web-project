package controllers;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.model.Order;
import services.CRUDOrderService;

@Path("/order")
public class OrderController {

	@Context
	ServletContext ctx;
	
	@GET
	@Path("/cancelorder/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String cancelOrder(@PathParam("id") UUID id) {
		CRUDOrderService service = new CRUDOrderService(ctx.getRealPath(""));
		
		return service.cancelOrder(id);
	}
	
	@POST
	@Path("/submitorder")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String submitOrder(ArrayList<Order> orders) {
		CRUDOrderService service = new CRUDOrderService(ctx.getRealPath(""));
		
		return service.submitOrders(orders);
	}
}

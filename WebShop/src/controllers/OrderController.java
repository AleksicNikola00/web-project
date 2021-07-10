package controllers;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.model.Order;
import dto.PastOrderDTO;
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
	
	@PUT
	@Path("/requestorder/{id}/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String requestOrder(@PathParam("id") UUID id,@PathParam("username") String username) {
		CRUDOrderService service = new CRUDOrderService(ctx.getRealPath(""));
		
		return service.requestOrder(id,username);
	}
	
	@PUT
	@Path("/delieverorder/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String delieverOrder(@PathParam("id") UUID id) {
		CRUDOrderService service = new CRUDOrderService(ctx.getRealPath(""));
		
		return service.delieveredOrder(id);
	}
	
	@POST
	@Path("/submitorder")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String submitOrder(ArrayList<Order> orders) {
		CRUDOrderService service = new CRUDOrderService(ctx.getRealPath(""));
		
		return service.submitOrders(orders);
	}
	
	@PUT
	@Path("/updateorder")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateOrder(PastOrderDTO order) {
		CRUDOrderService service = new CRUDOrderService(ctx.getRealPath(""));
		System.out.println("Upao");
		return service.updateOrderStatus(order);
	}
}

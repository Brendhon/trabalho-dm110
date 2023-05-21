package br.inatel.dm110.api.purchase;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/purchase")
public interface PurchaseService {

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(PurchaseTO purchase);

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PurchaseTO> listAll();

	@GET
	@Path("/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseTO getByInvoiceCode(@PathParam("code") String code);
	
	@PUT
	@Path("/{code}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(@PathParam("code") String code, PurchaseTO newPurchase);
	
}

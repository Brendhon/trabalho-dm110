package br.inatel.dm110.client.purchase;

import java.util.List;
import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.inatel.dm110.api.purchase.PurchaseTO;

public class PurchaseClient {

	// Invoice Code
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int CODE_LENGTH = 8;

	// URL to make request
	private static final String REST_URI_BASE = "http://localhost:8080/trabalho-web/api/purchase";

	// Client
	private static Client client = ClientBuilder.newClient();

	// Main
	public static void main(String[] args) {
		// Get new Invoice Code
		String invoiceCode = generateInvoiceCode();
		String time = "2020-08-08 12:12:12";

		// Purchase
		PurchaseTO purchase = new PurchaseTO();
		purchase.setInvoiceCode(invoiceCode);
		purchase.setOrder("Example Order");
		purchase.setCpf("12345678901");
		purchase.setDateTime(time);
		purchase.setValue(99.99);
		
		System.out.println("Purchase \n" + purchase.showAttr());

		// Create
		System.out.println("\nResult from POST: " + create(purchase));

		// Get by valid code
		System.out.println("\nResult from GET 0 \n" + getByCode(invoiceCode).showAttr());

		// Get all
		 System.out.println("\nResult from GET ALL: " + getAll().size());

		// New Purchase
		PurchaseTO newPurchase = new PurchaseTO();
		newPurchase.setInvoiceCode(invoiceCode);
		newPurchase.setOrder("Example new Order");
		newPurchase.setCpf("12345678901");
		newPurchase.setDateTime(time);
		newPurchase.setValue(150.99);

		// Update
		System.out.println("Update: " + update(newPurchase.getInvoiceCode(), newPurchase));
	}

	private static String update(String code, PurchaseTO newPurchase) {
		System.out.println("Entity.json: " + Entity.json(newPurchase));

		Response resp = client.target(REST_URI_BASE).path(String.valueOf(code)).request(MediaType.APPLICATION_JSON)
				.put(Entity.json(newPurchase));

		return "Status: " + resp.getStatus();
	}

	private static List<PurchaseTO> getAll() {
		List<PurchaseTO> list = client.target(REST_URI_BASE).request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<PurchaseTO>>() {
				});
		return list;
	}

	private static PurchaseTO getByCode(String code) {
		return client
				.target(REST_URI_BASE)
				.path(String.valueOf(code))
				.request(MediaType.APPLICATION_JSON)
				.get(PurchaseTO.class);
	}

	private static String create(PurchaseTO purchase) {						
		Response resp = client.target(REST_URI_BASE)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(purchase));
		
		return "Status: " + resp.getStatus();
	}

	public static String generateInvoiceCode() {
		StringBuilder sb = new StringBuilder(CODE_LENGTH);
		Random random = new Random();

		for (int i = 0; i < CODE_LENGTH; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			sb.append(randomChar);
		}

		return sb.toString();
	}
}

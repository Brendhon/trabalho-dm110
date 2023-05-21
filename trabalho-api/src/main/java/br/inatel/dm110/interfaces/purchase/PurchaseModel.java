package br.inatel.dm110.interfaces.purchase;

import java.util.List;

import br.inatel.dm110.api.purchase.PurchaseTO;

public interface PurchaseModel {

	public void save(PurchaseTO purchase);
	
	public List<PurchaseTO> listAll();
	
	public PurchaseTO getByInvoiceCode(String code);
	
	public void update(String code, PurchaseTO newPurchase);
}

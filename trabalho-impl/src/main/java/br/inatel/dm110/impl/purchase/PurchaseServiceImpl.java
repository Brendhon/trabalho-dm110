package br.inatel.dm110.impl.purchase;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import br.inatel.dm110.api.purchase.PurchaseService;
import br.inatel.dm110.api.purchase.PurchaseTO;
import br.inatel.dm110.interfaces.purchase.PurchaseLocal;

@RequestScoped
public class PurchaseServiceImpl implements PurchaseService {

	@EJB
	private PurchaseLocal purchaseBean;
	
	@Override
	public void save(PurchaseTO purchase) {
		purchaseBean.save(purchase);
	}

	@Override
	public List<PurchaseTO> listAll() {
		return purchaseBean.listAll();
	}

	@Override
	public PurchaseTO getByInvoiceCode(String code) {
		return purchaseBean.getByInvoiceCode(code);
	}

	@Override
	public void update(String code, PurchaseTO newPurchase) {
		purchaseBean.update(code, newPurchase);
	}

}

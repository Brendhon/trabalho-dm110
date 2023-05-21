package br.inatel.dm110.beans.purchase;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.inatel.dm110.api.purchase.PurchaseTO;
import br.inatel.dm110.interfaces.purchase.PurchaseLocal;

@Stateless	
@Local(PurchaseLocal.class)
public class PurchaseBean implements PurchaseLocal {

	private static Logger log = Logger.getLogger(PurchaseBean.class.getName());

	@Override
	public void save(PurchaseTO purchase) {
		log.info("Save");
	}

	@Override
	public List<PurchaseTO> listAll() {
		log.info("List All");
		return null;
	}

	@Override
	public PurchaseTO getByInvoiceCode(String code) {
		log.info("Get by invoice code");
		return null;
	}

	@Override
	public void update(String code, PurchaseTO newPurchase) {
		log.info("Update");
	}
}

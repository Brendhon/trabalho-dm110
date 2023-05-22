package br.inatel.dm110.beans.purchase;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.inatel.dm110.api.purchase.PurchaseTO;
import br.inatel.dm110.entities.purchase.PurchaseEntity;
import br.inatel.dm110.interfaces.purchase.PurchaseLocal;

@Stateless
@Local(PurchaseLocal.class)
public class PurchaseBean implements PurchaseLocal {

	private static Logger log = Logger.getLogger(PurchaseBean.class.getName());

	@EJB
	private PurchaseQueueSender queueSender;
	
	@PersistenceContext(unitName = "purchase_pu")
	private EntityManager em;

	@Override
	public void save(PurchaseTO purchase) {
		// Create entity
		PurchaseEntity entity = new PurchaseEntity();
		entity.setCpf(purchase.getCpf());
		entity.setDateTime(purchase.getDateTime());
		entity.setInvoiceCode(purchase.getInvoiceCode());
		entity.setOrder(purchase.getOrder());
		entity.setValue(purchase.getValue());	
		
		// Save
		em.persist(entity);
		
		// Send msg to queue
		queueSender.sendTextMessage(purchase.getInvoiceCode());
	}

	@Override
	public List<PurchaseTO> listAll() {
		log.info("List all");
		TypedQuery<PurchaseEntity> query = em.createQuery("from PurchaseEntity s", PurchaseEntity.class);
		List<PurchaseEntity> purchases = query.getResultList();
		return toCollectionAPIModel(purchases);
	}

	@Override
	public PurchaseTO getByInvoiceCode(String code) {
		log.info("Get by invoice code: " + code);
		// Get purchase by invoice code
		TypedQuery<PurchaseEntity> query = em
				.createQuery("from PurchaseEntity s where s.invoiceCode = :code", PurchaseEntity.class)
				.setParameter("code", code);

		// Return purchase if exists
		List<PurchaseEntity> purchases = query.getResultList();
		if (purchases.size() > 0) {
			return toCollectionAPIModel(purchases).get(0);
		}
		return null;
	}

	@Override
	public void update(String code, PurchaseTO newPurchase) {
		log.info("Update: " + code);

		// Get purchase by invoice code
		TypedQuery<PurchaseEntity> query = em
				.createQuery("from PurchaseEntity s where s.invoiceCode = :code", PurchaseEntity.class)
				.setParameter("code", code);

		// Update purchase if exists
		List<PurchaseEntity> purchases = query.getResultList();
		if (purchases.size() > 0) {
			PurchaseEntity purchase = purchases.get(0);
			purchase.setCpf(newPurchase.getCpf());
			purchase.setDateTime(newPurchase.getDateTime());
			purchase.setInvoiceCode(newPurchase.getInvoiceCode());
			purchase.setOrder(newPurchase.getOrder());
			purchase.setValue(newPurchase.getValue());
			em.merge(purchase);
		}
	}

	private List<PurchaseTO> toCollectionAPIModel(List<PurchaseEntity> purchaseList) {
		return purchaseList.stream().map(item -> {
			PurchaseTO pu = new PurchaseTO();
			pu.setCpf(item.getCpf());
			pu.setDateTime(item.getDateTime());
			pu.setInvoiceCode(item.getInvoiceCode());
			pu.setOrder(item.getOrder());
			pu.setValue(item.getValue());
			return pu;
		}).collect(Collectors.toList());
	}
}

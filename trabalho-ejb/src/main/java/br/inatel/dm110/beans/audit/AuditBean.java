package br.inatel.dm110.beans.audit;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.inatel.dm110.entities.audit.AuditEntity;

@Stateless
public class AuditBean {

	private static Logger log = Logger.getLogger(AuditBean.class.getName());
	
	@PersistenceContext(unitName = "purchase_pu")
	private EntityManager em;

    public void createAudit(String registerCode, String operation) {
    	log.info("Save Audit: " + registerCode + ", " + operation);
    	AuditEntity audit = new AuditEntity(registerCode, operation);
        em.persist(audit);
    }
}

package br.inatel.dm110.mdb.purchase;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.inatel.dm110.beans.audit.AuditBean;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/dm110queue") })
public class PurchaseQueueMDB implements MessageListener {
	private static Logger log = Logger.getLogger(PurchaseQueueMDB.class.getName());

	@EJB
	private AuditBean auditBean;
	
	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				TextMessage txtMessage = (TextMessage) message;
				String text = txtMessage.getText();

				// Split text message to get invoice code and operation
				String[] parts = text.split("-");
				String invoiceCode = parts[0];
				String operation = parts[1];

				log.info("Invoice code: " + invoiceCode);
				log.info("Operation: " + operation);
				
				// Save on DB
				auditBean.createAudit(invoiceCode, operation);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}

package br.inatel.dm110.mdb.purchase;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/dm110queue") })
public class PurchaseQueueMDB implements MessageListener {
	private static Logger log = Logger.getLogger(PurchaseQueueMDB.class.getName());
	
	@Override
	public void onMessage(Message message) {
		
		log.info("onMessage");
		try {
			if (message instanceof TextMessage) {
				TextMessage txtMessage = (TextMessage) message;
				String text = txtMessage.getText();
				log.info("Mensagem recebida da fila: " + text);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}

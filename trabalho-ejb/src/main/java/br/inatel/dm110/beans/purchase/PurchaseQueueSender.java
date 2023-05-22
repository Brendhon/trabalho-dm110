package br.inatel.dm110.beans.purchase;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Stateless
public class PurchaseQueueSender {
	private static Logger log = Logger.getLogger(PurchaseQueueSender.class.getName());
	
	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/dm110queue")
	private Queue queue;
	
	public void sendTextMessage(String text) {
		log.info("Send Text Message: " + text);
		try {
			Connection conn = connectionFactory.createConnection();
			Session session = conn.createSession();
			MessageProducer msgProducer = session.createProducer(queue);
			TextMessage txtMsg = session.createTextMessage(text);
			msgProducer.send(txtMsg);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
	
}

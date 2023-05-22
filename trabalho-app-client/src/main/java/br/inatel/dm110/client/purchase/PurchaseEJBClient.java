package br.inatel.dm110.client.purchase;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.inatel.dm110.interfaces.purchase.PurchaseRemote;

public class PurchaseEJBClient {

	public static void main(String[] args) throws Exception {
		invokeSessionBean();
	}

	private static void invokeSessionBean() throws NamingException {

		final PurchaseRemote purchase = lookupSessionHello();
		if (purchase != null) {
			// Invoke the call on the remote object
			System.out.println("Resultado da chamada ao session bean: " + purchase.listAll());
		} else {
			System.out.println("Objeto session bean remoto não encontrado.");
		}
	}

	private static PurchaseRemote lookupSessionHello() throws NamingException {
		// Does the lookup of the EJB (object) session bean

		String appName = "trabalho-ear-1.0";
		String moduleName = "trabalho-ejb-1.0";
		String beanName = "PurchaseBean";
		String interfaceName = PurchaseRemote.class.getName();

		// EJB full name
		String jndiName = "ejb:" + appName + "/" + moduleName + "/" + beanName + "!" + interfaceName;
		System.out.println("JNDI Name: " + jndiName);
		Context context = createInitialContext();
		return (PurchaseRemote) context.lookup(jndiName);
	}

	// JEE container access properties
	private static Context createInitialContext() throws NamingException {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
		return new InitialContext(jndiProperties);
	}
}

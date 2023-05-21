package br.inatel.dm110.rest.purchase;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.inatel.dm110.impl.purchase.PurchaseServiceImpl;

@ApplicationPath("/api")
public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(PurchaseServiceImpl.class);//register the class to publish the rest service
		return classes;
	}
}

package com.googlecode.jferry.example;

import com.googlecode.jferry.JaxRsClientProxyFactory;
import com.googlecode.jferry.testserver.IService;
import com.googlecode.jferry.testserver.Person;
import com.googlecode.jferry.testserver.ServerLauncher;

/**
 * This example shows, how to use jferry. The client requires a running testserver.
 * 
 * @author dseidel
 *
 */
public class Main {
	public static final void main(String... args) {
		// create a service proxy
		IService personService = JaxRsClientProxyFactory.createProxy(IService.class, ServerLauncher.SERVER_BASE_URI);
		
		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("Doe");
		personService.addPerson(person);
		
		System.out.println(personService.lookupPerson("Doe"));
	}
}

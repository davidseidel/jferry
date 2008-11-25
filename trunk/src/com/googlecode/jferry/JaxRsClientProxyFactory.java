package com.googlecode.jferry;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;

public class JaxRsClientProxyFactory {
	
	/**
	 * Creates a new client proxy to access restful webservices.
	 * 
	 * @param <T> type of service
	 * @param interfaze service class interface
	 * @param endpointUrl Url of the jax rs based endpoint
	 * @return client proxy to access restful webservices.
	 */
	public static <T> T createProxy(Class<T> interfaze, String endpointUrl) {
		InvocationHandler handler;
		T service = null;
		try {
			handler = new JaxRsAnnotationBasedProxy(interfaze, new URL(endpointUrl));
			Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[]{ interfaze }, handler);
			service = interfaze.cast(proxy);
		} catch (MalformedURLException e) {
			throw new IllegalStateException(e);
		}
		return service;
	}
	
}

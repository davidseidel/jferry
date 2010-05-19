package com.googlecode.jferry.testserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.jferry.testserver.service.ServiceResource;
import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

public class ServerLauncher {
	public static final String SERVER_BASE_URI = "http://localhost:9998/helloworld/";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Map<String, String> initParams = new HashMap<String, String>();

		initParams.put("com.sun.jersey.config.property.packages",
				ServiceResource.class.getPackage().getName());

		System.out.println("Starting grizzly...");
		System.out.println(ServiceResource.class.getPackage().getName());
		SelectorThread threadSelector;
		try {
			threadSelector = GrizzlyWebContainerFactory.create(SERVER_BASE_URI,
					initParams);

			System.out.println(String.format(
					"Jersey app started with WADL available at %sapplication.wadl\n"
							+ "Try out %shelloworld\nHit enter to stop it...",
					SERVER_BASE_URI, SERVER_BASE_URI));
			System.in.read();
			threadSelector.stopEndpoint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);

	}

}

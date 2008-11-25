package com.prodyna.ws.jaxrs.client.processor.model;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path(TestInterface.BASE_PATH)
public interface TestInterface {
	public static final String BASE_PATH = "/hello";
	
	@GET
	@Path("/{input}")
	String reverse(@PathParam("input") String input);
}

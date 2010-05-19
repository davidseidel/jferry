package com.googlecode.jferry.testserver;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/")
public interface IService {

	@Produces("text/xml")
	@GET
	@Path("person/{name}")
	Person lookupPerson(@PathParam("name") String name);
	

	@Produces("text/xml")
	@DELETE
	@Path("person/{name}")
	void deletePerson(@PathParam("name") String name);

	@Consumes("text/xml")
	@Produces("text/xml")
	@POST
	@Path("person")
	void addPerson(Person person);
	
	@Consumes("text/xml")
	@Produces("text/xml")
	@PUT
	@Path("person")
	void updatePerson(Person person);
}
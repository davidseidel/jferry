package com.googlecode.jferry.testserver.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.jferry.testserver.IService;
import com.googlecode.jferry.testserver.Person;
import com.googlecode.jferry.testserver.PersonList;


@Path("/")
public class ServiceResource implements IService {

	private static Logger logger = LoggerFactory.getLogger(ServiceResource.class);
	
	private static List<Person> persons = new ArrayList<Person>();
	
	@Context
	private HttpHeaders contentType;
	
	public ServiceResource() {
		
	}
	
	@Override
	public void addPerson(Person person) {
		logger.info(contentType.toString());
		synchronized (persons) {
			persons.add(person);	
			logger.info("added person: " + person);
		}
	}


	@Override
	public Person lookupPerson(String name) {
		logger.info("try to find person with last name: " + name);
		Person personToReturn = null;
		synchronized (persons) {
			for(Person person : persons) {
				if(person.getLastName().equals(name)) {
					personToReturn = person;
					break;
				}
			}
		}
		return personToReturn;
	}
	
	

	@Override
	public void updatePerson(Person personToUpdate) {
		Person person = this.lookupPerson(personToUpdate.getLastName());	
		if(person != null) {
			person.setFirstName(personToUpdate.getFirstName());
		}
	}

	@Override
	public void deletePerson(String name) {
		Person person = this.lookupPerson(name);	
		if(person != null) {
			logger.info("delete person" + person);
			persons.remove(person);
			Person deletedPerson = this.lookupPerson(name);
			if(deletedPerson == null) {
				logger.info("deleted person" + person);		
			}
		}
	}

	@Override
	public PersonList listPersons() {
		PersonList personList = new PersonList();
		personList.setPersons(persons);
		return personList;
	}

	@Override
	public Person addPersonJson(Person person) {
		this.addPerson(person);
		return person;
	}
}

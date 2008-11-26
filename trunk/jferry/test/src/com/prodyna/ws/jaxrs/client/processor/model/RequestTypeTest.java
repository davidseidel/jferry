package com.prodyna.ws.jaxrs.client.processor.model;

import java.lang.annotation.Annotation;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import junit.framework.Assert;

import org.junit.Test;

import com.googlecode.jferry.model.RequestType;


public class RequestTypeTest {

	@Test
	public void testGetAnnotationComparsion() {
		Class<? extends Annotation> getAnnotation = GET.class;
		RequestType getRequestType = RequestType.GET;
		Assert.assertTrue(getRequestType.testAnnotationOfRequestType(getAnnotation));
	}
	
	@Test
	public void testPostAnnotationComparsion() {
		Class<? extends Annotation> postAnnotation = POST.class;
		RequestType postRequestType = RequestType.POST;
		Assert.assertTrue(postRequestType.testAnnotationOfRequestType(postAnnotation));
	}
}

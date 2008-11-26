package com.googlecode.jferry.model;

import java.lang.annotation.Annotation;


public enum RequestType {
	GET(javax.ws.rs.GET.class),
	PUT(javax.ws.rs.PUT.class),
	DELETE(javax.ws.rs.DELETE.class),
	POST(javax.ws.rs.POST.class),
	HEAD(javax.ws.rs.HEAD.class);
	
	private Class<? extends Annotation> annotation;
	
	private RequestType(Class<? extends Annotation> annotation) {
		this.annotation = annotation;
	}
	
	public boolean testAnnotationOfRequestType(Class<? extends Annotation> annotationToTest) {
		if(annotationToTest == null) {
			throw new IllegalArgumentException("parameter annotationToTest should be not bull");
		}
		
		boolean result = false;
		
		if(annotationToTest.equals(this.annotation)) {
			result = true;
		}
		
		return result;
	}
}

package com.prodyna.ws.jaxrs.client.processor.invocation;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.googlecode.jferry.model.AnnotationBasedInterfaceMetadataFactory;
import com.googlecode.jferry.model.InterfaceMetadata;
import com.googlecode.jferry.model.MethodMetadata;
import com.googlecode.jferry.model.RequestType;
import com.prodyna.ws.jaxrs.client.processor.model.TestInterface;


public class PathProcessorTest {
	@Test
	public void testCreatePath() {
		InterfaceMetadata metaData = new AnnotationBasedInterfaceMetadataFactory().create(TestInterface.class);
		Assert.assertEquals(TestInterface.BASE_PATH, metaData.getBasePath());
		List<MethodMetadata> methods = metaData.getMethods();
		
		MethodMetadata methodMetadata = null;
		for(MethodMetadata method : methods) {
			Assert.assertNotNull(method.getRequestType());
			if(method.getRequestType().equals(RequestType.GET)) {
				methodMetadata = method;
			}
		}
		
		if(methodMetadata == null) {
			Assert.fail("no get request found");
		}
	}
}

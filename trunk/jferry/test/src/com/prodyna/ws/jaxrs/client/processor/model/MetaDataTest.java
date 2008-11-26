package com.prodyna.ws.jaxrs.client.processor.model;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.jferry.model.AnnotationBasedInterfaceMetadataFactory;
import com.googlecode.jferry.model.InterfaceMetadata;

public class MetaDataTest {
	private InterfaceMetadata metaData;
	
	@Before
	public void init() {
		metaData = new AnnotationBasedInterfaceMetadataFactory().create(TestInterface.class);
	}
	
	@Test
	public void testInterfaceMetaData() {	
		Assert.assertEquals(TestInterface.BASE_PATH, metaData.getBasePath());
	}
}

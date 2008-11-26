package com.googlecode.jferry;

import java.net.URL;

import com.googlecode.jferry.invocation.IMethodInvocationProcessorFactory;
import com.googlecode.jferry.invocation.commonshttpclient.MethodInvocationProcessorFactory;
import com.googlecode.jferry.model.AnnotationBasedInterfaceMetadataFactory;
import com.googlecode.jferry.model.AnnotationBasedMethodMetadataFactory;
import com.googlecode.jferry.model.IMethodMetadataFactory;
import com.googlecode.jferry.model.InterfaceMetadata;
import com.googlecode.jferry.model.InvocationContext;

public class JaxRsAnnotationBasedProxy extends AbstractClientProxy {
	private Class<?> interfaze;
	private URL baseUrl;
	private InterfaceMetadata interfaceMetadata;
	
	public JaxRsAnnotationBasedProxy(Class<?> interfaze, URL baseUrl) {
		this.interfaze = interfaze;
		this.interfaceMetadata = new AnnotationBasedInterfaceMetadataFactory().create(this.interfaze);
		this.baseUrl = baseUrl;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IMethodInvocationProcessorFactory getMethodInvocationProcessorFactory() {
		// TODO: must be configurable
		return new MethodInvocationProcessorFactory();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IMethodMetadataFactory getMethodMetadataFactory() {
		// TODO: must be configurable
		return new AnnotationBasedMethodMetadataFactory();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected InvocationContext createInvocationContext() {
		InvocationContext ctx = new InvocationContext();
		ctx.setBaseUrl(this.baseUrl);
		ctx.setBasePath(this.interfaceMetadata.getBasePath());
		return ctx;
	}




}

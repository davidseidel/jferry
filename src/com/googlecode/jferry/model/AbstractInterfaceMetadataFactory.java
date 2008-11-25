package com.googlecode.jferry.model;

import java.util.List;

public abstract class AbstractInterfaceMetadataFactory implements
		IInterfaceMetadataFactory {

	public AbstractInterfaceMetadataFactory() {
		super();
	}
	
	protected abstract String lookupBasePath(Class<?> interfaze);
	
	protected abstract List<MethodMetadata> lookupMethods(Class<?> interfaze);

	@Override
	public InterfaceMetadata create(Class<?> interfaze) {
		InterfaceMetadata metaData = new InterfaceMetadata();
		
		// get base path
		String basePath = this.lookupBasePath(interfaze);
		metaData.setBasePath(basePath);
		
		// get methods
		List<MethodMetadata> methods = this.lookupMethods(interfaze);
		metaData.setMethods(methods);
		
		return metaData;
	}



	

}
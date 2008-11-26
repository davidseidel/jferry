package com.googlecode.jferry.model;

import java.util.List;


public class InterfaceMetadata {
	private String basePath;
	
	private List<MethodMetadata> methods;

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	
	public List<MethodMetadata> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodMetadata> methods) {
		this.methods = methods;
	}
}

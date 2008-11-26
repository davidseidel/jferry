package com.googlecode.jferry.model;

public class MethodParameterMetadata {
	private boolean entityParameter = false;
	
	private boolean queryParameter = false;
	
	private String queryParameterName;
	
	private boolean pathParameter = false;
	
	private String pathParameterName;
	
	private Class<?> type;

	public boolean isEntityParameter() {
		return entityParameter;
	}

	public void setEntityParameter(boolean entityParameter) {
		this.entityParameter = entityParameter;
	}

	public boolean isQueryParameter() {
		return queryParameter;
	}

	public void setQueryParameter(boolean queryParameter) {
		this.queryParameter = queryParameter;
	}

	public String getQueryParameterName() {
		return queryParameterName;
	}

	public void setQueryParameterName(String queryParameterName) {
		this.queryParameterName = queryParameterName;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public boolean isPathParameter() {
		return pathParameter;
	}

	public void setPathParameter(boolean pathParameter) {
		this.pathParameter = pathParameter;
	}

	public String getPathParameterName() {
		return pathParameterName;
	}

	public void setPathParameterName(String pathParameterName) {
		this.pathParameterName = pathParameterName;
	}
}

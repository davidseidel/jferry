package com.googlecode.jferry.model;

import java.net.URL;

public class InvocationContext {
	private URL baseUrl;

	private String basePath;

	public URL getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(URL baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
}

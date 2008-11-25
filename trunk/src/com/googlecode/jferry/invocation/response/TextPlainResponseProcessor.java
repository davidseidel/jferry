package com.googlecode.jferry.invocation.response;

import javax.activation.MimeType;

public class TextPlainResponseProcessor implements IResponseProcessor {

	@Override
	public boolean canProcess(MimeType type) {
		if (type != null && type.getPrimaryType().equals("text")
				&& type.getSubType().equals("plain")) {
			return true;
		}
		return false;
	}

	@Override
	public Object process(Class<?> type, String responseString) {
		Object returnValue = null;
		try {
			
			if(type.equals(String.class)) {
				returnValue = responseString;
			}
			
			if(type.equals(Integer.class)) {
				returnValue = Integer.valueOf(responseString);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		
		if(returnValue != null) {
			return returnValue;			
		}
		
		throw new IllegalStateException("cannot process value of type " + type.getName());
		
	}

}

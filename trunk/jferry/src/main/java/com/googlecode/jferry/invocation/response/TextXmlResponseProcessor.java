package com.googlecode.jferry.invocation.response;

import java.io.Reader;
import java.io.StringReader;

import javax.activation.MimeType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class TextXmlResponseProcessor implements IResponseProcessor {

	@Override
	public boolean canProcess(MimeType type) {

		if (type != null && type.getPrimaryType().equals("text")
				&& type.getSubType().equals("xml")) {
			return true;
		}
		return false;

	}

	@Override
	public Object process(Class<?> type, String responseString) {
		Object returnValue = null;
		try {
			JAXBContext ctx = JAXBContext.newInstance(type);
			Unmarshaller unmarshaller = ctx.createUnmarshaller();

			Reader reader = new StringReader(responseString);
			returnValue = unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			new IllegalStateException(e);
		}

		return returnValue;
	}

}

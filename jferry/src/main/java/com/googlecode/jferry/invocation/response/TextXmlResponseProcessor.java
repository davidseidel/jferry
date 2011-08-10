package com.googlecode.jferry.invocation.response;

import java.io.Reader;
import java.io.StringReader;

import javax.activation.MimeType;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Processor to convert XML serialized objects to Java Beans by using JAXB.
 * 
 * @author David Seidel &lt;seidel.david@googlemail.com&gt;
 * 
 */
public class TextXmlResponseProcessor implements IResponseProcessor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canProcess(MimeType type) {

		if (MediaType.APPLICATION_XML.equals(type.toString()) || MediaType.TEXT_XML.equals(type.toString())) {
			return true;
		}
		return false;

	}

	/**
	 * {@inheritDoc}
	 */
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

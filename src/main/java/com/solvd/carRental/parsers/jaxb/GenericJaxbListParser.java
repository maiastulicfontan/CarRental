package com.solvd.carRental.parsers.jaxb;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//this parser will marshal/unmarshal a list of objects
public class GenericJaxbListParser<T> {
	private final static Logger LOGGER = LogManager.getLogger(GenericJaxbListParser.class);
	
	public static <T> List<T> jabxXmlToObjectList (Class<T> genericClass, String inputFilePath) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Wrapper.class, genericClass);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StreamSource streamSource = new StreamSource(new File(inputFilePath));
			Wrapper<T> wrapper = unmarshaller.unmarshal(streamSource, Wrapper.class).getValue();
			return wrapper.getItems();
		} catch (JAXBException e){
			LOGGER.error(e);
		}
		return null;
	}
	
	//@SuppressWarnings("rawtypes")
	public static <T> void jabxObjectListToXml (Class<T> genericClass, List<T> t, String outputFilePath, String collectionName) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Wrapper.class, genericClass);
			QName qualifiedName = new QName (collectionName);
			Marshaller marshaller = jaxbContext.createMarshaller();
			Wrapper <T> wrapper = new Wrapper<T>(t);
			JAXBElement<Wrapper> jaxbElement = new JAXBElement<Wrapper>(qualifiedName, Wrapper.class, wrapper); 
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); //output format with linefeeds and indentation
			marshaller.marshal(jaxbElement, new File(outputFilePath));
		} catch (JAXBException e) {
			LOGGER.error(e);
		}
	}
	

}

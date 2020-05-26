package com.solvd.carRental.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.carRental.models.Car;

public class CarJaxbParser {
	private final static Logger LOGGER = LogManager.getLogger(CarJaxbParser.class);
	
	public Car jaxbXmlToCar () {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Car.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (Car) unmarshaller.unmarshal(new File("src/main/resources/cars.xml"));
		} catch (JAXBException e) {
			LOGGER.error(e);
		}
		return null;
	}
	
	public void jaxbCarToXml (Car car) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Car.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); //output format with linefeeds and indentation
			marshaller.marshal(car, new File(""));
		} catch (JAXBException e) {
			LOGGER.error(e);
		}
		
	}
}

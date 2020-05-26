package com.solvd.carRental.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.carRental.models.CarModel;

public class CarModelJaxbParser {
	private final static Logger LOGGER = LogManager.getLogger(CarModelJaxbParser.class);
	
	public CarModel jaxbXmlToCarModel () {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CarModel.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (CarModel) unmarshaller.unmarshal(new File("src/main/resources/car-models.xml"));
		} catch (JAXBException e) {
			LOGGER.error(e);
		}
		return null;
	}
	
	public void jaxbCarModelToXml (CarModel carModel) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(CarModel.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(carModel, new File(""));
		} catch (JAXBException e) {
			LOGGER.error(e);
		}
		
	}
}

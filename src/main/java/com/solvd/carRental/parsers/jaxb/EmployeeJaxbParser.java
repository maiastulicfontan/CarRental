package com.solvd.carRental.parsers.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.carRental.models.Employee;

public class EmployeeJaxbParser {
	private final static Logger LOGGER = LogManager.getLogger(EmployeeJaxbParser.class);
	
	public Employee jaxbXmlToEmployee () {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (Employee) unmarshaller.unmarshal(new File("src/main/resources/employees.xml"));
		} catch (JAXBException e) {
			LOGGER.error(e);
		}
		return null;
	}
	
	public void jaxbEmployeeToXml (Employee employee) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Employee.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(employee, new File(""));
		} catch (JAXBException e) {
			LOGGER.error(e);
		}
		
	}
}

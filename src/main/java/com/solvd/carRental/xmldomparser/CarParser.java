package com.solvd.carRental.xmldomparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.solvd.carRental.models.Car;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CarParser {
	private final static Logger LOGGER = LogManager.getLogger(CarParser.class);
	
	public List<Car> parseCars(){
		List<Car> cars = new ArrayList<Car>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(new File("src/main/resources/cars.xml"));
	        doc.getDocumentElement().normalize();
	        LOGGER.info("Root element: "+doc.getDocumentElement().getNodeName());
	        NodeList nodeList = doc.getElementsByTagName("car");
	        
	        for (int i= 0; i < nodeList.getLength(); i++) {
	        	Node node = nodeList.item(i);
	        	if (node.getNodeType()== Node.ELEMENT_NODE) {
	        		Element element = (Element) node;
	        		Car tmpCar = this.getCarInfo(element);
	        		cars.add(tmpCar);
	        	}
	        }
		} catch (ParserConfigurationException | IOException | SAXException e) {
			LOGGER.error(e);
		}
		return cars;
	}
	
	public Car getCarInfo (Element element) {
		Car tmpCar = new Car();
		tmpCar.setId(Long.parseLong(element.getAttribute("id")));
		tmpCar.setLicensePlate(element.getElementsByTagName("license-plate").item(0).getTextContent());
		tmpCar.setModelYear(Integer.parseInt(element.getElementsByTagName("model-year").item(0).getTextContent()));
		tmpCar.setColor(element.getElementsByTagName("color").item(0).getTextContent());
		return tmpCar;
	}
}

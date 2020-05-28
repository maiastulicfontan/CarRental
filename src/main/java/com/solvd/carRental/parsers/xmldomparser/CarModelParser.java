package com.solvd.carRental.parsers.xmldomparser;

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

import com.solvd.carRental.models.CarBrand;
import com.solvd.carRental.models.CarModel;
import com.solvd.carRental.models.CarType;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CarModelParser {
	private final static Logger LOGGER = LogManager.getLogger(CarModelParser.class);
	
	public List<CarModel> parseCarModels (){
		List <CarModel> carModels = new ArrayList<CarModel>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(new File("src/main/resources/car-models.xml"));
	        doc.getDocumentElement().normalize();
	        LOGGER.info("Root element: "+doc.getDocumentElement().getNodeName());
	        NodeList nodeList = doc.getElementsByTagName("car-model");
	        
	        for (int i = 0; i < nodeList.getLength(); i++ ) {
	        	Node node = nodeList.item(i);
	        	if (node.getNodeType() == Node.ELEMENT_NODE) {
	        		Element element = (Element) node;
	        		CarModel tmpCarModel = this.getCarModelInfo(element);
	        		NodeList carBrand = element.getElementsByTagName("carbrand");
	        		Node nodeCarBrand = carBrand.item(0);
	        		Element elementCarBrand = (Element) nodeCarBrand;
	        		if (carBrand.getLength() > 0) {
	        			tmpCarModel.setBrand(this.getCarBrandInfo(elementCarBrand));
	        		}
	        		NodeList carType = element.getElementsByTagName("car-type");
	        		Node nodeCarType = carType.item(0);
	        		Element elementCarType = (Element) nodeCarType;
	        		if (carType.getLength() > 0) {
	        			tmpCarModel.setType(this.getCarTypeInfo(elementCarType));
	        		}
	        		carModels.add(tmpCarModel);		
	        	}
	        }

		} catch (ParserConfigurationException | IOException | SAXException e) {
			 LOGGER.error(e);
		}
		return carModels;
	}
	
	public CarModel getCarModelInfo(Element element) {
		CarModel tmpCarModel = new CarModel();
		tmpCarModel.setId(Long.parseLong(element.getAttribute("id")));
		tmpCarModel.setName(element.getElementsByTagName("name").item(0).getTextContent());
		tmpCarModel.setDescription(element.getElementsByTagName("description").item(0).getTextContent());
		tmpCarModel.setTransmission(element.getElementsByTagName("transmission").item(0).getTextContent());
		tmpCarModel.setNumberOfSeats(Integer.parseInt(element.getElementsByTagName("number-of-seats").item(0).getTextContent()));
		tmpCarModel.setAirbagInfo(element.getElementsByTagName("airbag-info").item(0).getTextContent());
		tmpCarModel.setLuggageSpace(element.getElementsByTagName("luggage-space").item(0).getTextContent());
		tmpCarModel.setFuelConsumption(element.getElementsByTagName("fuel-consumption").item(0).getTextContent());
		tmpCarModel.setCostPerDay(Double.parseDouble(element.getElementsByTagName("cost-per-day").item(0).getTextContent()));
		return tmpCarModel;
	}
	
	public CarBrand getCarBrandInfo (Element elementCarBrand) {
		CarBrand tmpCarBrand = new CarBrand();
		tmpCarBrand.setId(Long.parseLong(elementCarBrand.getAttribute("id")));
		tmpCarBrand.setName(elementCarBrand.getElementsByTagName("name").item(0).getTextContent());
		return tmpCarBrand;
	}
	
	public CarType getCarTypeInfo (Element elementCarType) {
		CarType tmpCarType = new CarType();
		tmpCarType.setId(Long.parseLong(elementCarType.getAttribute("id")));
		tmpCarType.setName(elementCarType.getElementsByTagName("name").item(0).getTextContent());
		return tmpCarType;
		
	}
}



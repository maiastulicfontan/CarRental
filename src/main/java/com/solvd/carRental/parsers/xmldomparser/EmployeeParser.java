package com.solvd.carRental.parsers.xmldomparser;

import com.solvd.carRental.models.Employee;
import com.solvd.carRental.models.EmployeePosition;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeParser {
	private final static Logger LOGGER = LogManager.getLogger(EmployeeParser.class);

	public List<Employee> parseEmployees () {
		List <Employee> employees = new ArrayList<Employee>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(new File("src/main/resources/employees.xml"));
	        doc.getDocumentElement().normalize();
	        LOGGER.info("Root element: "+doc.getDocumentElement().getNodeName());
	        NodeList nodeList = doc.getElementsByTagName("employee");
	        
	        for (int i = 0; i < nodeList.getLength(); i++ ) {
	        	Node node = nodeList.item(i);
	        	if (node.getNodeType() == Node.ELEMENT_NODE) {
	        		Element element = (Element) node;
	        		Employee tmpEmp =this.getEmployeeInfo(element);	        		
	        		NodeList empPos = element.getElementsByTagName("position");
	        		Node nodeEmpPos = empPos.item(0);
	        		Element elementEmpPos = (Element) nodeEmpPos;
	        		if (empPos.getLength() > 0) {
	        			tmpEmp.setPosition(this.getEmpPositionInfo(elementEmpPos));
	        		}
	        		employees.add(tmpEmp);		
	        	}
	        }

		} catch (ParserConfigurationException | IOException | SAXException e) {
			 LOGGER.error(e);
		}
		return employees;
	}
	
	public Employee getEmployeeInfo (Element element) {
		Employee tmpEmp = new Employee();
		tmpEmp.setId(Long.parseLong(element.getAttribute("id")));
		tmpEmp.setFirstName(element.getElementsByTagName("first-name").item(0).getTextContent());
		tmpEmp.setLastName(element.getElementsByTagName("last-name").item(0).getTextContent());
		tmpEmp.setBirthDate(LocalDate.parse(element.getElementsByTagName("birth-date").item(0).getTextContent()));
		tmpEmp.setNationalGovernmentId(element.getElementsByTagName("national-gvt-id").item(0).getTextContent());
		tmpEmp.setHireDate(LocalDate.parse(element.getElementsByTagName("hire-date").item(0).getTextContent()));
		return tmpEmp;
	}
	
	public EmployeePosition getEmpPositionInfo (Element elementEmpPos) {
		EmployeePosition tmpEmpPos = new EmployeePosition();
		tmpEmpPos.setId(Long.parseLong(elementEmpPos.getAttribute("id")));
		tmpEmpPos.setName(elementEmpPos.getElementsByTagName("name").item(0).getTextContent());
		tmpEmpPos.setDescription(elementEmpPos.getElementsByTagName("description").item(0).getTextContent());
		tmpEmpPos.setSalary(Double.parseDouble(elementEmpPos.getElementsByTagName("salary").item(0).getTextContent()));
		return tmpEmpPos;
	}
}

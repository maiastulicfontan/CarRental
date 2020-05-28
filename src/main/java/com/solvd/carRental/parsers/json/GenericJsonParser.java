package com.solvd.carRental.parsers.json;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class GenericJsonParser<T> {
	private final static Logger LOGGER = LogManager.getLogger(GenericJsonParser.class);
	
	public List<T> jsonToObjectList (String inputFilePath){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			List<T> objects = objectMapper.readValue(new File(inputFilePath), new TypeReference<List<T>>(){});
			return objects;
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return null;
	}
	
	public void objectListToJson(List<T> objects, String outputFilePath) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(outputFilePath), objects);
		} catch (JsonMappingException e) {
			LOGGER.error(e);
		} catch (JsonGenerationException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}
}

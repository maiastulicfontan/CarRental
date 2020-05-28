package com.solvd.carRental.parsers.jaxb;
import javax.xml.bind.annotation.XmlAnyElement;
import java.util.List;
import java.util.ArrayList;

public class Wrapper <T>{
	 private List<T> items;

	  public Wrapper() {
		  items = new ArrayList<T>();
	  }

	  public Wrapper(List<T> items) {
	      this.items = items;
	  }

	  @XmlAnyElement(lax=true)
	  public List<T> getItems() {
	      return items;
	  }
}

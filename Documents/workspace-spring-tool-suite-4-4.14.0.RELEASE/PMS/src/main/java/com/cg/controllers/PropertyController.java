package com.cg.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Property;
import com.cg.services.PropertyService;


@RestController
@RequestMapping("/property")
public class PropertyController {
		
	     @Autowired
	     private PropertyService ps; 
	     
	     @GetMapping("/getAllProperty")
	     public List<Property> getAllProperty(){
	    	 return ps.getAllProperty();
	     }

	    @PostMapping("/addProperty")
	  	public ResponseEntity<Property> addProperty(@Valid @RequestBody Property property)
	  	{
	  		Property addedProperty = ps.addProperty(property);
	  		return new ResponseEntity<Property>(addedProperty,HttpStatus.CREATED);
	  	}
	      
	     @GetMapping("/findProperty/{id}")
	     public ResponseEntity<Object> findProperty(@PathVariable("id") long id ){
	    	 
	  	             return ps.findProperty(id);  
	     }
	     
	     @GetMapping("/propertyType/{type}")
	     public List<Property> findPropertyByType(@PathVariable("type") String type ){
	  	   return ps.findPropertyType(type);
	     }
	     
	     @GetMapping("/propertyAvl/{purpose}")
	     public List<Property> findPropertyByAvalability(@PathVariable("purpose") String purpose ){
	  	   return ps.findPropertyAvalability(purpose);
	     }
	     
	     @GetMapping("/propertyPrice/{purpose}/{type}/{price}")
	     public List<Property> findPropertyByPrice(@PathVariable("purpose") String purpose , @PathVariable("type") String type ,@PathVariable("price") double price){
	  	   return ps.findPropertyByPrice(purpose,type ,price);
	     }
	     
	     @GetMapping("/propertySortByPrice")
	     public List<Property> getAllPropertySortedByPrice(){
	    	 return ps.getAllPropertySortedByPrice();
	     }
	     
	     @GetMapping("/propertySortBySize")
	     public List<Property> getAllPropertySortedBySize(){
	    	 return ps.getAllPropertySortedBySize();
	     }
	     
	     @DeleteMapping("/delProperty/{id}")
	     public String deleteProperty(@PathVariable("id") long  id) {
	    	 return ps.deleteProperty(id);
	     }

    
}
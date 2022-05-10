package com.cg.services;

import java.util.List;
import javax.el.PropertyNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.entities.Property;
import com.cg.repositories.PropertyRepository;


@Service
public class PropertyService {
   @Autowired
   private PropertyRepository propertyRepository;

   
   public List<Property> getAllProperty(){
	    return propertyRepository.findAll();
   }

   public Property addProperty(Property property) {
  	  return propertyRepository.save(property);
  	  
   }
   
   public ResponseEntity<Object> findProperty(long id ){
	   
	   if(!propertyRepository.existsById(id))  throw new PropertyNotFoundException();
	   
	   return new ResponseEntity<Object>(  propertyRepository.findById(id).get() ,HttpStatus.OK);
	   
   }
   
   public List<Property> findPropertyType( String type ){
	  return propertyRepository.findByType(type);   
   }
   
   public List<Property> findPropertyAvalability( String purpose ){
	   return propertyRepository.findByAvailability(purpose);
   }
 
   public List<Property> findPropertyByPrice( String purpose , String type , double price){
	   return propertyRepository.findByPrice(purpose,type ,price);
   }
   
   public List<Property> getAllPropertySortedByPrice(){
  	 return propertyRepository.findAll(Sort.by("price"));
   }
   
   public List<Property> getAllPropertySortedBySize(){
	  	 return propertyRepository.findAll(Sort.by("sizeOfApartment"));
	   }
   
   public String deleteProperty( long  id) {
	   
  	 propertyRepository.deleteById(id);
  	 return "Deleted SucessFully";
   }
   
   public Property getPropertyByName(String name)
   {
	   return propertyRepository.findByPropertyName(name);
   }
   
   public String setPropertySold(long id, String sold)
   {
	   propertyRepository.findById(id).get().setSellOrRent(sold);
	   
	   return "status changed";
   }
   
}
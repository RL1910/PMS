package com.cg.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Buyer;
import com.cg.entities.Preference;
import com.cg.entities.Property;
import com.cg.exception.PersonNotFountException;
import com.cg.services.BuyerService;
import com.cg.services.PropertyService;
import com.cg.services.TransactionService;

@RestController
@RequestMapping("/buyer")
public class BuyerController {
	
	@Autowired
	private BuyerService buyerService;
	
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/setAppointmentDate/{personId}")
	public String setAppointmentDate(@PathVariable("personId") long personId, @RequestBody LocalDate date) throws PersonNotFountException{
		return buyerService.setAppointmentDate(personId, date); 
	}
	
	@GetMapping("/getAppointmentDateAndTime/{personId}")
	public String getAppointmentDateAndTime( @PathVariable("personId") long personId) throws PersonNotFountException {
		return buyerService.getAppointmentDateAndTime(personId);
	}
	
	@GetMapping("/getPreferences/{personId}")
	public Preference getPreferences(@PathVariable("personId") long personId) throws PersonNotFountException {
		return buyerService.getPreferences(personId);
	}
	
	@GetMapping("/getAllBuyers")
	public List<Buyer> getAllBuyers(){
		return buyerService.getAllBuyers();
	}
	
	@DeleteMapping("/deleteBuyer/{personId}")
	public ResponseEntity<Object> deleteBuyer(@PathVariable("personId") long personId) throws PersonNotFountException{
		return buyerService.deleteBuyer(personId);
	}
	
	@PostMapping("/changePreferences/{personId}")
	public Object changePreferences(@PathVariable("personId") long personId , @RequestBody  Preference preferences) throws PersonNotFountException{
		
		if(!buyerService.existOrNot(personId)) return "BuyerId"+" -> "+personId+" : does not exist";
		
		return buyerService.changePreferences(personId, preferences);
		
	}
	
	@PostMapping("/addBuyer")
	public Buyer addBuyer(@RequestBody Buyer buyer)
	{
		return buyerService.addBuyer(buyer);
		
	}
	
	@GetMapping("/getAllProperty")
	public List<Property> getAllProperty()
	{
		return propertyService.getAllProperty();
	}
	
	@GetMapping("/buyProperty/{buyerId}/{propertyId}")
	public Object buyProperty(@PathVariable("buyerId") long buyerId ,@PathVariable("propertyId") long propertyId) 
	{
		return  transactionService.buyProperty(buyerId , propertyId );
	}
}

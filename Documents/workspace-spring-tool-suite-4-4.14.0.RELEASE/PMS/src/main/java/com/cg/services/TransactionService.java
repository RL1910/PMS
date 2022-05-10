package com.cg.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.Buyer;
import com.cg.entities.Property;
import com.cg.entities.Tenant;
import com.cg.entities.TransactionDetails;
import com.cg.repositories.BuyerRepository;
import com.cg.repositories.TenantRepository;
import com.cg.repositories.TransactionRepository;

@Service
public class TransactionService {
	
		
		@Autowired
		PropertyService propertyService;
		
		@Autowired
		TransactionRepository transactionRepos;
		
		@Autowired
		TransactionService transactionService;
		
		@Autowired
		BuyerService buyerService;
		
		@Autowired
		BuyerRepository buyerRepos;
		
		@Autowired
		TenantRepository tenantrepos;
		
		
		public Object buyProperty(long buyerId ,long propertyId) 
		{
		
		Property property= propertyService.getAllProperty().stream().filter(eachProperty ->
		eachProperty.getSellOrRent().equals("forSale") && eachProperty.getPropertyId()==propertyId).findAny().get();
		                                       
		if(property == null) return "This property is not for buy";
		
		
		
		Buyer buyer=buyerRepos.findById(buyerId).get();
		
		if(buyer==null)  return "This buyerId is not exist";
		
		buyer.setProperty(property);
		
		if(!property.getSellOrRent().equals("sold") && !property.getSellOrRent().equals("Rented") )
		{
		
				
			TransactionDetails transactionDetails=new TransactionDetails();
			transactionDetails.setPropertyName(property.getPropertyName());
			transactionDetails.setAmmount(property.getPrice());
			transactionDetails.setBuyerName(buyer.getPersonName());
			transactionDetails.setOwnerName(property.getOwner().getPersonName());
			transactionDetails.setProperty(property);
			String buydate=LocalDate.now().toString();
			transactionDetails.setTransactionDate(buydate);
			
			
			propertyService.setPropertySold(propertyId, "sold");
		
		
		    return transactionRepos.save(transactionDetails);
		
		}
		
		    return "its already sold";
		
		}
		
		public Object rentProperty(long tenantId ,long propertyId) 
		{
			Property property;
			
			try {
				
		       property= propertyService.getAllProperty().stream().filter(eachProperty ->
		       eachProperty.getSellOrRent().equals("forRent") && eachProperty.getPropertyId()==propertyId).findAny().get();
		       
		       }
			
			catch (Exception exception){
				
				return "propertyId does not exist";
				
		}
			
		if(property == null) return "This property is not for rent";
		
		Tenant tenant=tenantrepos.findById(tenantId).get();
		
		if(tenant==null)  return "This tenantId is not exist";
		
		tenant.setProperty(property);
		
		if(!property.getSellOrRent().equals("sold") && !property.getSellOrRent().equals("Rented"))
		{
		
					
				TransactionDetails transactionDetails=new TransactionDetails();
				transactionDetails.setPropertyName(property.getPropertyName());
				transactionDetails.setAmmount(property.getPrice());
				transactionDetails.setTenantName(tenant.getPersonName());
				transactionDetails.setOwnerName(property.getOwner().getPersonName());
				transactionDetails.setProperty(property);
				String buydate=LocalDate.now().toString();
				transactionDetails.setTransactionDate(buydate);
				
				
				propertyService.setPropertySold(propertyId, "Rented");
				
				return transactionRepos.save(transactionDetails);
				
				}
				return "its already sold or Rented";
				
		
	    }
	
		public List<Property> getAllProperty()
		{
			return propertyService.getAllProperty().stream().filter(f -> f.getSellOrRent().equals("forSale") ||
					f.getSellOrRent().equals("forRent")  ).collect(Collectors.toList());
		}
		
		public List<TransactionDetails> getAllTransactionDetails()
		{
			return transactionRepos.findAll();
		}
		
		public TransactionDetails getTransactionDetailsByBuyer(String buyerName)
		{
			return transactionRepos.findByBuyerName(buyerName);
		}
		
		public double getAllMoney()
		{
			return transactionRepos.findAll().stream().map(tr -> tr.getAmmount()).reduce(Double :: sum).get();
		}
		
		public double getAllRentMoney()
		{
			return transactionRepos.findAll().stream().filter(tf -> tf.getTenantName()!=null).map(tr -> tr.getAmmount()).reduce(Double :: sum).get();
		}
	     
		
	

}

package com.cg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cg.entities.Staff;
import com.cg.exception.PersonNotFountException;
import com.cg.repositories.StaffRepository;

@Service
public class StaffService {
	
	@Autowired
	private StaffRepository staffRepository;
	
	public String addStaff(Staff staff) {
		
		staffRepository.save(staff);
		return "New Staff Added"; 
	}
	
	public List<Staff> getAllStaff() {
		
		return staffRepository.findAll();
	}
	
	public ResponseEntity<Object> deleteStaff(long personId) throws PersonNotFountException{
		if(!staffRepository.existsById(personId)) {
			throw new PersonNotFountException("Person not found!!");
		}
		else {
			staffRepository.deleteById(personId);
			return new ResponseEntity<>("record deleted successfully",HttpStatus.OK);
		}
	}
	
	public List<String> getServices(long personId) throws PersonNotFountException{
		if(!staffRepository.existsById(personId)) {
			throw new PersonNotFountException("Person not found!!");
		}
		else {
			Staff staff = staffRepository.findById(personId).get();
			return staff.getServices();
		}
	}
	
	public boolean getStaffAvailabilityStatus(long personId) throws PersonNotFountException{
		if(!staffRepository.existsById(personId)) {
			throw new PersonNotFountException("Person not found!!");
		}
		else {
			Staff staff = staffRepository.findById(personId).get();
			return staff.getIsAvailable();
		}
	}
	
	public Staff getStaffInfo(long personId) throws PersonNotFountException{
		if(!staffRepository.existsById(personId)) {
			throw new PersonNotFountException("Person not found!!");
		}
		else {
		Staff staff = staffRepository.findById(personId).get();
		return staff;
		}
	}
	
	public List<Staff> updateStaffAvailability(long personId, boolean isAvailable) throws PersonNotFountException{
		if(!staffRepository.existsById(personId)) {
			throw new PersonNotFountException("Person not found!!");
		}
		else {
			Staff staff = staffRepository.findById(personId).get();
			staff.setIsAvailable(isAvailable);
			staffRepository.save(staff);
			return staffRepository.findAll();
		}
	}
	
}

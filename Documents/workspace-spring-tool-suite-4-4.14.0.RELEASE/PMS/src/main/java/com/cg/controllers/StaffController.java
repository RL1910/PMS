package com.cg.controllers;

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

import com.cg.entities.Staff;
import com.cg.exception.PersonNotFountException;
import com.cg.services.StaffService;


@RestController
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	
	
	@PostMapping("addStaff")
	public String addStaff(@RequestBody Staff staff) {
		return staffService.addStaff(staff);
	}
	
	@GetMapping("getAllStaff")
	public List<Staff> getAllStaff() {
		return staffService.getAllStaff();
	}
	
	@DeleteMapping("/deleteStaff/{personId}")
	public ResponseEntity<Object> deleteStaff(@PathVariable("personId")  long personId) throws PersonNotFountException{
		return staffService.deleteStaff(personId);
	}
	
	@GetMapping("/getStaffAssignedTasks/{personId}")
	public List<String> getStaffAssignedTasks(@PathVariable("personId") long personId) throws PersonNotFountException {
		return staffService.getServices(personId);
	}
	
	@GetMapping("/getStaffAvailability/{personId}")
	public boolean getStaffAvailabilityStatus(@PathVariable("personId") long personId) throws PersonNotFountException {
		return staffService.getStaffAvailabilityStatus(personId);
	}
	
	@GetMapping("/getStaffInfo/{personId}")
	public Staff getStaffInfo(@PathVariable("personId")  long personId) throws PersonNotFountException {
		return staffService.getStaffInfo(personId);
	}
	
	@PostMapping("updateStaff/{personId}/{isAvailable}")
	public List<Staff> updateStaffAvailability(@PathVariable("personId")  long personId, 
			@PathVariable("isAvailable") boolean isAvailable) throws PersonNotFountException{
		return staffService.updateStaffAvailability(personId, isAvailable);
	}

}

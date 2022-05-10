package com.cg.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.Maintenance;
import com.cg.repositories.MaintenanceRepository;

@Service
public class MaintenanceService {
	
	@Autowired
	MaintenanceRepository mrepos;
	
	public List<Maintenance> getAllMaintenance()
	{
		return mrepos.findAll();
	}
	
	public Maintenance getMaintenanceById(long id)
	{
		return mrepos.findById(id).get();
	}
	
	public Maintenance addMaintenance( Maintenance maintenance)
	{
		return mrepos.save(maintenance);
	}
	
	public String deleteMaintenanceById(long id)
	{
		mrepos.deleteById(id);
		return "deleted";
	}
	
	public Maintenance updateMaintenance(Maintenance maintenance)
	{
		if(mrepos.existsById(maintenance.getId()))
		{
			mrepos.deleteById(maintenance.getId());
		}
		
		return mrepos.save(maintenance);
	}
	
	public List<Maintenance> sortMaintenanceByPrize()
	{
		return mrepos.findAll().stream().sorted(Comparator.comparing(Maintenance :: getPrize)).
				collect(Collectors.toList());
	}
	

}

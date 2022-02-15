package com.doctor.project;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceLayer {
	
	@Autowired
	Repository repo;

	public List<DoctorClass> getDoctors() {
		
		
		
		return repo.findAll();
	}

	public DoctorClass addd(DoctorClass data) {
		List<String> dList = Arrays.asList("MS","MBBS and MD","Surgeon","MS","MCH");
	    if(dList.contains(data.getDegree())) {
	    	return repo.save(data);
	    }
		
	    return null;
	}

	

}

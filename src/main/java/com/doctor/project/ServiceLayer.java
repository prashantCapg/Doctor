package com.doctor.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
public class ServiceLayer {
	
	@Autowired
	Repository repo;

	public List<DoctorClass> getDoctors(String str) {
		
		List<DoctorClass> D = repo.findAll();
		return D.stream().filter(temp->temp.getSpecialities().equalsIgnoreCase(str)).collect(Collectors.toList());
		
		
	}

	public DoctorClass addd(DoctorClass data) {
		List<String> dList = Arrays.asList("MS","MBBS and MD","Surgeon","MS","MCH");
	    if(dList.contains(data.getDegree())) {
	    	return repo.save(data);
	    }else {
	    	throw new DegreeEmptyException("error");
	    }
	}
	public String addd1(DoctorClass data)  {
		List<String> dList = Arrays.asList("MS","MBBS and MD","Surgeon","MS","MCH");
		
		if(dList.contains(data.getDegree())) {
	    	repo.save(data);
	    }else {
	    	if((data.getDegree().isEmpty())) {
	    		return "degree is empty";
	    	}
	    }
		
		return "data added successfully";
		
		
		
		
	}

	public List<DoctorClass> getAlpha(String str) {
		List<DoctorClass> D = repo.findAll();
		return D.stream().filter(temp->temp.getDoctorName().startsWith(str)).collect(Collectors.toList());
	}

	public Optional<DoctorClass> getDocLarLength() {
		List<DoctorClass> D = repo.findAll();
		
		return D.stream().max(Comparator.comparingInt(t->t.getDoctorName().length()));
		
		//DoctorClass a = Collections.max(D, Comparator.comparing(obj -> obj.getDoctorName().length()));
		//return a;
		//.max(Comparator.comparingInt(t->t.getDoctorName().length()));
	}

	public List<DoctorClass> getDoc() {
		return repo.findAll();
	}

	public String deleDat() {
		repo.deleteAll();
		return "successfully deleted data";
	}

	public String delParDat(String deg) {
		List<DoctorClass> DC = repo.findAll();
		for(DoctorClass var : DC) {
			if(var.getDegree().equalsIgnoreCase(deg)) {
				repo.delete(var);
			}
		}
		return "doctor with given degree deleted";
	}

	public List<DoctorClass> updaDocNa(DoctorClass data, String s) {
		List<DoctorClass> Doc = repo.findAll();
		Doc = Doc.stream().map(temp->{
			if(temp.getDoctorName().equals(s)) {
				temp.setDoctorName(data.getDoctorName());
				
			}
			return temp;
		}).collect(Collectors.toList()); 
		repo.saveAll(Doc);
		return Doc;
		
	}

	
	public List<DoctorClass> updateDoctorDeg(DoctorClass data, String s) {
		List<DoctorClass> Doc = repo.findAll();
		Doc = Doc.stream().map(temp->{
			if(temp.getDoctorName().equals(s)) {
				temp.setDoctorName(data.getDoctorName());
				temp.setDegree(data.getDegree());
			}
			return temp;
		}).collect(Collectors.toList()); 
		repo.saveAll(Doc);
		return Doc;
		
	}
	

	

	

	

	

}

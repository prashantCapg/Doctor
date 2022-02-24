package com.doctor.project;

import java.util.Arrays;
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
	
	public void setRepo(Repository repo) {
		this.repo = repo;
	}

	//this method fetch doctor with particular specialties
	public List<Doctor> getDoctors(String str) {
		
		List<Doctor> D = repo.findAll();
		return D.stream().filter(temp->temp.getSpecialities().equalsIgnoreCase(str)).collect(Collectors.toList());
		
		
	}
	
	public Doctor addd(Doctor data) throws DegreeEmptyException{
		List<String> dList = Arrays.asList("MS","MBBS and MD","Surgeon","MS","MCH","MD");
	    if(dList.contains(data.getDegree())) {
	    	return repo.save(data);
	    }else {
	    	throw new DegreeEmptyException("Enter correct degree");
	    }
	}
	//this method add data in a table only if assigned degree matches with the given degree list
	public String addd1(Doctor data) throws Exception, DegreeEmptyException  {
		List<String> dList = Arrays.asList("MS","MBBS and MD","Surgeon","MS","MCH");
		
		if(dList.contains(data.getDegree())) {
	    	 repo.save(data);
	    }else if(data.getDegree().isEmpty()) {
	    	throw new DegreeEmptyException("Degree Can not be empty");
	    }
		else {
			
	    	throw new DegreeNotFoundException("Degree should be in the given list");
	    }
		
		return "New Doctor is added successfully";
		
		
		
		
	}
	//this method fetch the data with the first letter of doctor name
	public List<Doctor> getAlpha(String str) {
		List<Doctor> D = repo.findAll();
		
		return D.stream().filter(temp->temp.getDoctorName().startsWith(str)).collect(Collectors.toList());
	}
	//this method returns a Doctor name with longest length
	public Optional<Doctor> getDocLarLength() {
		List<Doctor> D = repo.findAll();
		
		return D.stream().max(Comparator.comparingInt(t->t.getDoctorName().length()));
		
		//DoctorClass a = Collections.max(D, Comparator.comparing(obj -> obj.getDoctorName().length()));
		//return a;
		//.max(Comparator.comparingInt(t->t.getDoctorName().length()));
	}
	//this method fetch all the current data
	public List<Doctor> getDoc() throws DataIsEmptyException {
		if(repo.findAll().isEmpty()) {
			throw new DataIsEmptyException("Data is empty");
		}else {
			return repo.findAll();
		}
	}
	//this method delete all data in a given list
	public String deleDat() {
		repo.deleteAll();
		return "successfully deleted data";
	}
	//this method delete doctors name with given degree 
	public String delParDat(String deg) {
		List<Doctor> DC = repo.findAll();
		for(Doctor var : DC) {
			if(var.getDegree().equalsIgnoreCase(deg)) {
				repo.delete(var);
			}
		}
		return "doctor with given degree deleted";
	}
	//this method update Doctor name in the list.
	public String updaDocNa(Doctor data, String s) {
		List<Doctor> Doc = repo.findAll();
		Doc = Doc.stream().map(temp->{
			if(temp.getDoctorName().equals(s)) {
				temp.setDoctorName(data.getDoctorName());
				
			}
			return temp;
		}).collect(Collectors.toList()); 
		repo.saveAll(Doc);
		return "Doctor name is updated";
		
	}

	//this method update Doctor name with degree.
	public String updateDoctorDeg(Doctor data, String s) {
		List<Doctor> Doc = repo.findAll();
		Doc = Doc.stream().map(temp->{
			if(temp.getDoctorName().equals(s)) {
				temp.setDoctorName(data.getDoctorName());
				temp.setDegree(data.getDegree());
			}
			return temp;
		}).collect(Collectors.toList()); 
		repo.saveAll(Doc);
		
		return "list is updated with current doctor and degree";
		
	}
	

	

	

	

	

}

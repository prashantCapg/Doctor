package com.doctor.project;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.project.Exception.DataIsEmptyException;
import com.doctor.project.Exception.DegreeNotFoundException;
import com.doctor.project.Exception.NameNotFoundException;

@RestController
public class Controller {
	@Autowired
	ServiceLayer svl;
	
	@GetMapping("/getAllDoctor")
	public ResponseEntity getDoctor() throws DataIsEmptyException {
		return new ResponseEntity(svl.getDoctor(),HttpStatus.OK);
	}
	@GetMapping("/getDoctorDetailsOfParticularSpecialities/{str}")
	public ResponseEntity getDoctorOfGivenSpecialities(@PathVariable String str){
		return new ResponseEntity(svl.getDoctorOfGivenSpecialities(str),HttpStatus.OK);
	}
	@GetMapping("/startWithAlphabet/{str}")
	public ResponseEntity getDoctorWithGivenAlphabet(@PathVariable String str) throws NameNotFoundException, DataIsEmptyException {
		return new ResponseEntity(svl.getDoctorWithGivenAlphabet(str),HttpStatus.OK);
	}
	@GetMapping("/getDoctorOfLongestLength")
	public ResponseEntity getDoctorLargestLength() {
		return new ResponseEntity(svl.getDoctorLargestLength(), HttpStatus.OK);
	}
	@PostMapping("/addDoctorNameWithGivenDegree")
	public ResponseEntity addDoctorWithGivenDegree(@Valid@RequestBody Doctor data) {
		
		
		 
		return new ResponseEntity(svl.addDoctorWithGivenDegree(data),HttpStatus.CREATED);
	}
	@PostMapping("/addDoctorWithNonEmptyDegree")
	public ResponseEntity addDoctorWithNonEmptyDegree(@Valid@RequestBody Doctor data) throws Exception   {
		return new ResponseEntity(svl.addDoctorWithNonEmptyDegree(data), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity deleteData() throws DataIsEmptyException  {
		return new ResponseEntity(svl.deleteAllData(), HttpStatus.OK);
	}
	@DeleteMapping("/deleteByDegree/{Deg}")
	public ResponseEntity deletePartData(@PathVariable String Deg) throws DegreeNotFoundException, DataIsEmptyException {
		return new ResponseEntity(svl.deleteDoctorWithGivenDegree(Deg), HttpStatus.OK);
	}
	@PutMapping("/updateDoctorName/{Name}")
	public ResponseEntity<Doctor> updateDoctorName(@Valid@RequestBody Doctor data,@PathVariable("Name") String str) {
		return new ResponseEntity(svl.updaDoctorName(data,str), HttpStatus.OK);
	}
	@PutMapping("/updateDocAndDegName/{Name}")
	public ResponseEntity<Doctor> updateDoctorAndDegreeName(@Valid@RequestBody Doctor data, @PathVariable("Name") String str){
		return new ResponseEntity(svl.updateDoctorAndDegreeName(data, str), HttpStatus.OK);
	}
	

}

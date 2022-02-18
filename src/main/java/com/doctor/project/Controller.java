package com.doctor.project;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@Autowired
	ServiceLayer svl;
	
	@GetMapping("/getAllDoctor")
	public ResponseEntity getDoctor(){
		return new ResponseEntity(svl.getDoc(),HttpStatus.OK);
	}
	@GetMapping("/getDoctorDetailsOfParticularDegree/{str}")
	public ResponseEntity getAllDoctors(@PathVariable String str){
		return new ResponseEntity(svl.getDoctors(str),HttpStatus.OK);
	}
	@GetMapping("/startWithAlphabet/{str}")
	public ResponseEntity getDocAlpha(@PathVariable String str){
		return new ResponseEntity(svl.getAlpha(str),HttpStatus.OK);
	}
	@GetMapping("/getDoctorOfLongestLength")
	public ResponseEntity getDocLarLen() {
		return new ResponseEntity(svl.getDocLarLength(), HttpStatus.OK);
	}
	@PostMapping("/addDoctorNameWithGivenDegree")
	public ResponseEntity addDoctor(@Valid@RequestBody Doctor data) {
		
		/*
		 * if(data.getDegree().isEmpty()) { throw new
		 * DegreeEmptyException("Degree shouldn't be empty"); }
		 */
		 
		return new ResponseEntity(svl.addd(data),HttpStatus.CREATED);
	}
	@PostMapping("/addDoctorWithNonEmptyDegree")
	public ResponseEntity addDoctor1(@Valid@RequestBody Doctor data) throws Exception   {
		return new ResponseEntity(svl.addd1(data), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity deleteData() {
		return new ResponseEntity(svl.deleDat(), HttpStatus.OK);
	}
	@DeleteMapping("/deleteByDegree/{Deg}")
	public ResponseEntity deletePartData(@PathVariable String Deg) {
		return new ResponseEntity(svl.delParDat(Deg), HttpStatus.OK);
	}
	@PutMapping("/updateDoctorName/{Name}")
	public ResponseEntity<Doctor> updateDocName(@Valid@RequestBody Doctor data,@PathVariable("Name") String str) {
		return new ResponseEntity(svl.updaDocNa(data,str), HttpStatus.OK);
	}
	@PutMapping("/updateDocAndDegName/{Name}")
	public ResponseEntity<Doctor> updateDocDeg(@Valid@RequestBody Doctor data, @PathVariable("Name") String str){
		return new ResponseEntity(svl.updateDoctorDeg(data, str), HttpStatus.OK);
	}
	

}

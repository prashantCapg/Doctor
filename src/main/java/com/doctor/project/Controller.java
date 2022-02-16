package com.doctor.project;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@Autowired
	ServiceLayer svl;
	
	@GetMapping("/getAllDoctor")
	public List<DoctorClass> getDoctor(){
		return svl.getDoc();
	}
	@GetMapping("/getDoctorDetailsOfParticularDegree/{str}")
	public List<DoctorClass> getAllDoctors(@PathVariable String str){
		return svl.getDoctors(str);
	}
	@GetMapping("/startWithAlphabet/{str}")
	public List<DoctorClass> getDocAlpha(@PathVariable String str){
		return svl.getAlpha(str);
	}
	@GetMapping("/getDoctorOfLongestLength")
	public Optional<DoctorClass> getDocLarLen() {
		return svl.getDocLarLength();
	}
	@PostMapping("/addDoctorNameWithGivenDegree")
	public DoctorClass addDoctor(@RequestBody DoctorClass data) {
		
		return svl.addd(data);
	}
	@PostMapping("/addDoctorWithNonEmptyDegree")
	public String addDoctor1(@RequestBody DoctorClass data)  {
		return svl.addd1(data);
	}
	
	@DeleteMapping("/deleteAll")
	public String deleteData() {
		return svl.deleDat();
	}
	@DeleteMapping("/deleteByDegree/{Deg}")
	public String deletePartData(@PathVariable String Deg) {
		return svl.delParDat(Deg);
	}
	

}

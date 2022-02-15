package com.doctor.project;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@Autowired
	ServiceLayer svl;
	
	@GetMapping("/getAllDoctorDetails/")
	public List<DoctorClass> getAllDoctors(){
		return svl.getDoctors();
	}
	@PostMapping("/addDoctorName")
	public DoctorClass addDoctor(@RequestBody DoctorClass data) {
		
		return svl.addd(data);
	}
	

}

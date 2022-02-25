package com.doctor.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/*import com.doctor.project.Doctor;
import com.doctor.project.ServiceLayer;*/
import com.doctor.project.Doctor;
import com.doctor.project.DoctorApplication;
import com.doctor.project.ServiceLayer;
import com.doctor.project.Exception.DataIsEmptyException;

//@SpringBootConfiguration
@SpringBootTest(classes = DoctorApplication.class)
@AutoConfigureMockMvc
public class DoctorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ServiceLayer service;

	//controller class test
	@Test
	void showDoctorSuccessTest() throws Exception {
	List<Doctor> l=new ArrayList<>();
	Doctor p=new Doctor(1,"prashant","MD","Medicine");
	l.add(p);
	//when(service.showAll()).thenReturn(l);
	mockMvc.perform(get("/getAllDoctor"))
	.andExpect(status().isOk());
	verify(service,times(1)).getDoc();



	}
	
	  @Test void showDoctorTestError() throws Exception { 
	  
		  when(service.getDoc().isEmpty()).thenThrow(DataIsEmptyException.class);
		  mockMvc.perform(get("/getAllDoctor"))
		  .andExpect(status().is4xxClientError())
		  .andExpect(result ->
		  assertTrue(result.getResolvedException() instanceof DataIsEmptyException));
		   
		  verify(service,times(1)).getDoc();
	  
	  
	  }
	 

	



	}




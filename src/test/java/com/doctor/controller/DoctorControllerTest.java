package com.doctor.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.doctor.project.DoctorApplication;
import com.doctor.project.ServiceLayer;

@SpringBootTest(classes = DoctorApplication.class)
@AutoConfigureMockMvc
public class DoctorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ServiceLayer service;

	@Test
	void showDoctorSuccessTest() throws Exception {
		mockMvc.perform(get("/getAllDoctor")).andExpect(status().isOk());
		verify(service, times(1)).getDoctor();
	}

	@Test
	void showDoctorTestError() throws Exception {
		mockMvc.perform(get("/getAlloctor")).andExpect(status().is4xxClientError());
	}

	@Test
	void deleteDataTest() throws Exception {
		mockMvc.perform(delete("/deleteAll")).andExpect(status().isOk());
	}

}

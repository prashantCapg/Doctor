package com.doctor.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.doctor.project.Doctor;
import com.doctor.project.Repository;
import com.doctor.project.ServiceLayer;
import com.doctor.project.Exception.DataIsEmptyException;
@SpringBootConfiguration
@SpringBootTest
public class DoctorServiceTest {

	@Mock
	Repository repo;

	@InjectMocks
	ServiceLayer service;
	
	/*
	 * @BeforeEach public void setUp(){ MockitoAnnotations.initMocks(this); }
	 */




	@Test
	void showAllSuccessTest() throws DataIsEmptyException {
		when(repo.findAll()).thenReturn(Arrays.asList(new Doctor(1,"prashant","MD","Medicine")));
		List<Doctor> docList = service.getDoctor();
		assertFalse(docList.isEmpty());
		assertEquals(1, docList.size());
	
		//assertEquals(docList.get(0).getId(), 1);
		verify(repo,times(2)).findAll();
	}

	/*
	 * @Test void showAllTestError() throws DataIsEmptyException {
	 * when(repo.findAll()).thenReturn(Arrays.asList(new Doctor()));
	 * service.getDoc();
	 * when(repo.findAll().isEmpty()).thenThrow(DataIsEmptyException.class);
	 * verify(repo,times(2)).findAll(); }
	 */



}


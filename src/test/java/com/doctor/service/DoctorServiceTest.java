package com.doctor.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.doctor.project.Doctor;
import com.doctor.project.Repository;
import com.doctor.project.ServiceLayer;
import com.doctor.project.Exception.DataIsEmptyException;
import com.doctor.project.Exception.DegreeEmptyException;

@SpringBootConfiguration
@SpringBootTest
public class DoctorServiceTest {

	@Mock
	Repository repo;

	@InjectMocks
	ServiceLayer service;
	
	@BeforeEach
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void showAllDoctorSuccessTest() throws DataIsEmptyException  {
		when(repo.findAll()).thenReturn(Arrays
				.asList(new Doctor(1,"prashant","MD","Medicine")));
		List<Doctor> docList = service.getDoctor();
		assertFalse(docList.isEmpty());
		assertEquals(1, docList.size());
	}

	@Test
	void showAllDoctorTestError() throws DataIsEmptyException {

		assertTrue(repo.findAll().isEmpty());
		when(repo.findAll().isEmpty()).thenThrow(DataIsEmptyException.class);

	}

	@Test
	void addDoctorWithGivenDegreeTest() {
		Doctor data = new Doctor(1, "prashant", "MD", "Medicine");
		List<String> dList = Arrays
				.asList("MS", "MBBS and MD", "Surgeon", "MS", "MCH", "MD");
		service.addDoctorWithGivenDegree(data);
		assertEquals(true, dList.contains(data.getDegree()));
		verify(repo, times(1)).save(data);
	}

	@Test
	void addDoctorWithGivenDegreeExceptionTest() {
		Doctor data = new Doctor(1, "prashant", "", "Medicine");
		assertThrows(DegreeEmptyException.class,
				() -> service.addDoctorWithGivenDegree(data));
	}

	@Test
	void deleteAllDataErrorTest() {
		when(repo.findAll().isEmpty()).thenThrow(DataIsEmptyException.class);
	}

	@Test
	void deleteAllDataTest() {

		when(repo.findAll()).thenReturn(Arrays
				.asList(new Doctor(1, "prashant", "MD", "Medicine")));

		String actual = "successfully deleted data";
		assertEquals(service.deleteAllData(), actual);
		verify(repo, times(1)).deleteAll();
	}

	@Test
	void updaDoctorNameTest() {
		Doctor data = new Doctor(1, "prashant", "", "Medicine");
		assertEquals("Doctor name is updated", service.updaDoctorName(data, "prashant"));
	}
	  
  
}


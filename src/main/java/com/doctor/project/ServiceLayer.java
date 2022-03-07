package com.doctor.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctor.project.Exception.DataIsEmptyException;
import com.doctor.project.Exception.DegreeEmptyException;
import com.doctor.project.Exception.DegreeNotFoundException;
import com.doctor.project.Exception.DataNotFoundException;

@Service
public class ServiceLayer {
	
	@Autowired
	Repository repo;

	public void setRepository(Repository repo) {
		this.repo = repo;
	}

	// this method fetch all the current data
	public List<Doctor> getDoctor() throws DataIsEmptyException {
		if (repo.findAll().isEmpty()) {
			throw new DataIsEmptyException("Data is empty");
		} else {
			return repo.findAll();
		}
	}

	public List<Doctor> getDoctorWithGivenAlphabet(String str)
			throws DataNotFoundException, DataIsEmptyException {
		List<Doctor> doctor = repo.findAll();
		// List<Doctor> filterData = new ArrayList<Doctor>();
		if (repo.findAll().isEmpty()) {
			throw new DataIsEmptyException("List is Empty");
		} else {

			List<Doctor> filterData = doctor.stream()
					.filter(temp -> temp.getDoctorName().startsWith(str))
					.collect(Collectors.toList());
			if (filterData.isEmpty()) {
				throw new DataNotFoundException("Name not found");
			} else {
				return filterData;
			}
		}
	}

	// this method fetch doctor with particular specialties
	public List<Doctor> getDoctorOfGivenSpecialities(String str) {
		List<Doctor> doctor = repo.findAll();
		if (repo.findAll().isEmpty()) {
			throw new DataNotFoundException("Data is empty");
		} else {
			return doctor.stream()
					.filter(temp -> temp.getSpecialities().equalsIgnoreCase(str))
					.collect(Collectors.toList());
		}

	}

	public List<Doctor> getDoctorLargestLength() {
		List<Doctor> doctor = repo.findAll();
		int longest = 0;
		for (Doctor temp : doctor) {
			longest = Math.max(longest, temp.getDoctorName().length());
		}
		List<Doctor> result = new ArrayList<Doctor>();

		for (Doctor temp : doctor) {
			if (temp.getDoctorName().length() == longest) {
				result.add(temp);
			}
		}
		return result;
	}

	public Doctor addDoctorWithGivenDegree(Doctor data) throws DegreeEmptyException {
		List<String> doctorList = Arrays
				.asList("MS", "MBBS and MD", "Surgeon", "MS", "MCH", "MD");
		if (doctorList.contains(data.getDegree())) {
			return repo.save(data);
		} else {
			throw new DegreeEmptyException("Enter correct degree");
		}
	}

	// this method add data in a table only if assigned degree matches with the
	// given degree list
	public String addDoctorWithNonEmptyDegree(Doctor data)
			throws Exception, DegreeEmptyException {
		List<String> doctorList = Arrays
				.asList("MS", "MBBS and MD", "Surgeon", "MS", "MCH");

		if (doctorList.contains(data.getDegree())) {
			repo.save(data);
		} else if (data.getDegree().isEmpty()) {
			throw new DegreeEmptyException("Degree Can not be empty");
		} else {
			throw new DegreeNotFoundException("Degree should be in the given list");
		}
		return "New Doctor is added successfully";
	}

	// this method delete all data in a given list
	public String deleteAllData() {
		if (repo.findAll().isEmpty()) {
			throw new DataIsEmptyException("Data is empty");
		} else {
			repo.deleteAll();
			return "successfully deleted data";
		}
	}

	// this method delete doctors name with given degree
	public String deleteDoctorWithGivenDegree(String deg)
			throws DegreeNotFoundException, DataIsEmptyException {
		List<Doctor> doctor = repo.findAll();

		if (repo.findAll().isEmpty()) {
			throw new DataIsEmptyException("Data is empty");
		} else {
			List<Doctor> result = doctor.stream()
					.filter(temp -> temp.getDegree().equalsIgnoreCase(deg))
					.collect(Collectors.toList());
			if (result.isEmpty()) {
				throw new DegreeNotFoundException("Degree is not in the list");
			} else {
				repo.deleteAll(result);
				return "doctor with given degree deleted";
			}
		}
	}

	// this method update Doctor name in the list.
	public String updaDoctorName(Doctor data, String s) {
		List<Doctor> doctor = repo.findAll();
		doctor = doctor.stream().map(temp -> {
			if (temp.getDoctorName().equals(s)) {
				temp.setDoctorName(data.getDoctorName());
			}
			return temp;
		}).collect(Collectors.toList());
		repo.saveAll(doctor);
		return "Doctor name is updated";

	}

	// this method update Doctor name with degree.
	public String updateDoctorAndDegreeName(Doctor data, String s) {
		List<Doctor> doctor = repo.findAll();
		doctor = doctor.stream().map(temp -> {
			if (temp.getDoctorName().equals(s)) {
				temp.setDoctorName(data.getDoctorName());
				temp.setDegree(data.getDegree());
			}
			return temp;
		}).collect(Collectors.toList());
		repo.saveAll(doctor);

		return "list is updated with current doctor and degree";
	}
}

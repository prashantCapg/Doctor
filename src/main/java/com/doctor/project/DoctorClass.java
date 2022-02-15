package com.doctor.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="doctorDetails")
public class DoctorClass {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String doctorName;
	private String degree;
	private String specialities;
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getSpecialities() {
		return specialities;
	}
	public void setSpecialities(String specialities) {
		this.specialities = specialities;
	}
	
	
	@Override
	public String toString() {
		return "DoctorClass [doctorName=" + doctorName + ", degree=" + degree + ", specialities=" + specialities + "]";
	}

}

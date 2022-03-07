package com.doctor.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="doctorDetails")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotEmpty(message="Name should not be empty")
	@Size(min=3,max=20,message="Name should be 3 to 20 characters")
	private String doctorName;
	private String degree;
	
	private String specialities;
	public Doctor() {
		
	}
	public Doctor(Integer id, String doctorName, String degree, String specialities) {
		//super();
		this.id = id;
		this.doctorName = doctorName;
		this.degree = degree;
		this.specialities = specialities;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
		return "DoctorClass [id=" + id + ", doctorName=" + doctorName + ", degree=" + degree + ", specialities="
				+ specialities + "]";
	}
	
	

}

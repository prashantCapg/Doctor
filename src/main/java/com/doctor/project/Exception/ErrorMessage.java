package com.doctor.project.Exception;

import java.time.LocalDate;
import java.util.Date;


public class ErrorMessage {
	
	private String Message;
	private LocalDate date;
	public LocalDate getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = LocalDate.now();
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public ErrorMessage(String message, Date date) {
		super();
		Message = message;
		this.date = LocalDate.now();
	}

	

	
	
}

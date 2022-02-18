package com.doctor.project;

import java.util.Date;

public class ErrorMessage {
	
	private String Message;
	private Date date;
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
		this.date = date;
	}

	
	
}

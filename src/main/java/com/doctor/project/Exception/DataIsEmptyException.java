package com.doctor.project.Exception;

public class DataIsEmptyException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DataIsEmptyException(String str) {
		super(str);
	}
}

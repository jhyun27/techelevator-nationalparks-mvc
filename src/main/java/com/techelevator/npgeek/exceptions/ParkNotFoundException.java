package com.techelevator.npgeek.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParkNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 6255883192884319597L; //Unique identifier for this class
	
	private String parkCode;
	
	public ParkNotFoundException(String parkCode, String message) {
		super(message);
		this.parkCode = parkCode;
	}
	
	public String getParkCode() {
		return this.parkCode;
	}
}

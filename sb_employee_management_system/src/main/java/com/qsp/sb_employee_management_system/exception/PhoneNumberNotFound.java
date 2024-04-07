package com.qsp.sb_employee_management_system.exception;

public class PhoneNumberNotFound extends RuntimeException {
   private String message;

public PhoneNumberNotFound(String message) {
	super();
	this.message = message;
}
   @Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
}

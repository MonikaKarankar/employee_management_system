package com.qsp.sb_employee_management_system.exception;

public class NoDataAvailable extends RuntimeException{
  private String message;

public NoDataAvailable(String message) {
	super();
	this.message = message;
	
}
@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

  
}

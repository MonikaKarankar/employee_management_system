package com.qsp.sb_employee_management_system.exception;

public class EmailNotFound extends RuntimeException{
   private String message;

public EmailNotFound(String message) {
	super();
	this.message = message;
}
      @Override
    	public String getMessage() {
    		// TODO Auto-generated method stub
    		return message;
    	}
}

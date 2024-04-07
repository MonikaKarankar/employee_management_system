package com.qsp.sb_employee_management_system.exception;
public class IdNotFound extends RuntimeException{
  private String message;

public IdNotFound(String message) {
	super();
	this.message = message;
}
  @Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
}

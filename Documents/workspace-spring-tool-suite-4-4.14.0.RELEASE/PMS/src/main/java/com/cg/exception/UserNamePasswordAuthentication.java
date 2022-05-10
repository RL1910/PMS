package com.cg.exception;

public class UserNamePasswordAuthentication extends Exception{
	
	private static final long serialVersionUID = 5L;
	
	public UserNamePasswordAuthentication(String password) {
		super(password);
		
	}

}

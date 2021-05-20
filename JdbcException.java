package com.bridgelabz;

public class JdbcException extends Exception {
	
    public String errormessage;
    
    //Initializing Constructor
	public JdbcException(String message) {
		this.errormessage = message;	
	}
}

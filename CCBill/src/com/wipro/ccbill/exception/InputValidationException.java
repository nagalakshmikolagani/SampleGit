package com.wipro.ccbill.exception;

public class InputValidationException extends Exception{
	private static final long serailVersionUID=1L;
	
	@Override
	public String toString()
	{
		return "Invalid Input Data";
	}

}

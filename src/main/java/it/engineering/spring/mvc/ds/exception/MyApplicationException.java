package it.engineering.spring.mvc.ds.exception;

public class MyApplicationException extends Exception{
	
	private static final long serialVersionUID = -7669713866855025643L;

	public MyApplicationException(String message) {
		super(message);
	}
}

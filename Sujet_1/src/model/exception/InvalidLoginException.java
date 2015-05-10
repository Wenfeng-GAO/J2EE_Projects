package model.exception;

public class InvalidLoginException extends Exception {

	private static final long serialVersionUID = -5625504039597150969L;
	
	public InvalidLoginException(String msg) {
		super(msg);
	}
	
	public InvalidLoginException(Throwable cause, String msg) {
		super(msg,cause);
	}	

}

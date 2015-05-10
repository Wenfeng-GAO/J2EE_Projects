package model.db;

public class DatabaseAccessError extends Exception {

	private static final long serialVersionUID = -3095087570836221287L;
	
	public DatabaseAccessError(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DatabaseAccessError(String arg0) {
		super(arg0);
	}

}

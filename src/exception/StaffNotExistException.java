package exception;

public class StaffNotExistException extends Exception{
	public StaffNotExistException() {
		super("Staff login ID does not exist");
	}
	
	public StaffNotExistException(String message) {
		super(message);
	}

}

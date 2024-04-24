package exception;

public class CloseBranchException extends Exception{
	public CloseBranchException() {
		super("Cannot close branch due to existing staff");
	}
	
	public CloseBranchException(String message) {
		super(message);
	}

}

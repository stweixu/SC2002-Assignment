package exception;

public class AddStaffException extends Exception {
    public AddStaffException(){
        super("Cannot add staff");
    }

    public AddStaffException(String message){
        super(message);
    }
}

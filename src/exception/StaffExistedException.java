package exception;

public class StaffExistedException extends Exception {
    public StaffExistedException(){
        super("Staff's ID already existed");
    }

    public StaffExistedException(String message){
        super(message);
    }
}

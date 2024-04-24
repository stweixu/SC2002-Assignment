package exception;

public class WrongPasswordException extends Exception {
    public WrongPasswordException(){
        super("Password is incorrect");
    }

    public WrongPasswordException(String message){
        super(message);
    }
}

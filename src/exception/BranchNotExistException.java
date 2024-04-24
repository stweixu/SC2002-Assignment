package exception;

public class BranchNotExistException extends Exception {
    public BranchNotExistException(){
        super("Branch does not exist");
    }

    public BranchNotExistException(String message){
        super(message);
    }
}

package exception;

public class BranchExistedException extends Exception {
    public BranchExistedException(){
        super("Branch's name already existed");
    }

    public BranchExistedException(String message){
        super(message);
    }
}

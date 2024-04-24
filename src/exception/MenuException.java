package exception;

public class MenuException extends Exception{
  public MenuException(){
        super("Menu does not exist!");
    }

    public MenuException(String message){
        super(message);
    }
}

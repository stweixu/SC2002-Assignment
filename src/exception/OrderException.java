package exception;

public class OrderException extends Exception{
  public OrderException() {
    super("Order does not exist");
  }
  
  public OrderException(String message) {
    super(message);
  }
}

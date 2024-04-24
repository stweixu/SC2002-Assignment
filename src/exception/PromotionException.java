package exception;

public class PromotionException extends Exception {
    public PromotionException(){
        super("Unable to promote staff");
    }

    public PromotionException(String message){
        super(message);
    }
}

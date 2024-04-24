package control.customer;

public class PaymentController{
	
	public boolean processPayment(double total, double payment) {
		if (payment>=total)	return true;
		else return false;
	}
}

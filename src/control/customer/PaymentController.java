package control.customer;

public class PaymentController{

	/**
     * processPayment() check if payment provide by customer is sufficient
     * @param total The total amount to pay
	* @param total The amount to given by customer
     * @return boolean
     */
	
	public boolean processPayment(double total, double payment) {
		if (payment>=total)	return true;
		else return false;
	}
}

package control.customer;

import java.util.Scanner;

import entity.Company;

public class PayNowController extends PaymentController{
	
	private String phoneNo;
	
	public PayNowController(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
     * processPayNowPayment() process PayNow payment method for customer
     * @param total The total amount to pay
     * @return boolean
     */
	
	public boolean processPayNowPayment(double total) {
		
		Scanner sc = new Scanner(System.in);
		double payment = 0;
		
		System.out.print("Enter Y to pay: ");
		char select = sc.next().charAt(0);
		if (select=='Y')	payment = total;
	
		if (processPayment(total,payment)) {
			System.out.printf("Paid: %.2f\n", total);
			System.out.printf("Phone No: %s\n", phoneNo);
			System.out.println("Thank you!");
			return true;
		}
		else {
			System.out.println("Payment not successful, exiting");
			return false;
		}
	}
}

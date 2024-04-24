package control.customer;

import java.util.Scanner;

import entity.Company;

public class CreditCardController extends PaymentController{
	
	private String cardNo;
	
	public CreditCardController(String cardNo) {
		this.cardNo = cardNo;
	}
	
	public boolean processCardPayment(double total) {
		
		Scanner sc = new Scanner(System.in);
		double payment = 0;
		
		System.out.print("Press Y to pay: ");
		char select = sc.next().charAt(0);		
	
		if (select=='Y')	payment = total;
		
		if (processPayment(total,payment)){
			System.out.printf("Paid: %.2f\n", total);
			System.out.printf("Card No: %s\n", cardNo);
			System.out.println("Thank you!");
			return true;
		}
		else {
			System.out.println("Payment not successful, exiting");
			return false;
		}
	}
}

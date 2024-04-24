package control.customer;

import java.util.Scanner;

import entity.Company;

public class CashController extends PaymentController{
	public boolean processCashPayment(double total) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("Total: $%.2f\n", total);
		System.out.printf("Enter amount to pay: $%.2f - $", total);
		double payment = sc.nextDouble();
	
		if (processPayment(total,payment)) {
			System.out.printf("Total: %.2f\n", total);
			System.out.printf("Received: %.2f\n", payment);
			System.out.printf("Change: %.2f\n", payment-total);
			System.out.println("Thank you!");
			return true;
		}
		else {
			System.out.println("Insufficient funds, exiting");
			return false;
		}
	}
}

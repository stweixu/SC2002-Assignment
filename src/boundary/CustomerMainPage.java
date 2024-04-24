package boundary;

import java.util.Scanner;
import java.util.ArrayList;
import entity.*;
import control.*;
import control.customer.CashController;
import control.customer.CreditCardController;
import control.customer.CustomerViewInterface;
import control.customer.OrderCheckOutInterface;
import control.customer.OrderCollectionInterface;
import control.customer.OrderCreationController;
import control.customer.PayNowController;

public class CustomerMainPage {

  static Scanner sc = new Scanner(System.in);
  private static String branchName, orderID;

  public static void CustomerMainPageUI() {

    System.out.println("Welcome Customer!\nPlease enter Branch by their name:\n");
    // Display branch names
    CompanyController.displayBranch();
    branchName = sc.next();
    int choice;
    
    do {
      System.out.println("What would you like to do?");
      System.out.println("1: Check placed order with your Order ID");
      System.out.println("2: Collect your order");
      System.out.println("3: Create a new order");
      System.out.println("Press any number to return to main page");

      choice = sc.nextInt();

      switch (choice) {
        case (1):
          viewOrderStatus(branchName);
          break;
        case (2):
          collectOrder(branchName);
          break;
        case (3):
          newOrderProcess(branchName);
          break;
      }
    }
    while (choice>=1 && choice<4);
    
  }

  public static void newOrderProcess(String branchName) {

    CustomerViewInterface viewer = new OrderViewController();
    OrderCheckOutInterface checkout = new OrderStatusController();
    int takeaway, choice, qty, index;
    String remarks, text;

    do {
      System.out.println("Select 1) Dine-In, 2) Takeaway");
      takeaway = sc.nextInt();
    } while (takeaway < 1 || takeaway > 2);

    orderID = OrderCreationController.createOrder(branchName, ((takeaway == 1) ? false : true));

    do {
      System.out.println("What would you like to do?");
      System.out.println("1: Add a menu item to your cart");
      System.out.println("2: Edit a menu item in your cart");
      System.out.println("3: Delete a menu item from your cart");
      System.out.println("4: View order cart");
      System.out.println("5: Check out your cart for payment");

      choice = sc.nextInt();
      
      switch (choice) {
        case (1): // add a menu item to my cart
          MenuViewController.displayMenu(branchName);

          System.out.println("Select item to add to order: ");
          index = sc.nextInt();
          System.out.println("Enter quantity?: ");
          qty = sc.nextInt();
          sc.nextLine();

          System.out.println("Any remarks?: ");
          remarks = sc.nextLine();

          OrderCreationController.addToOrder(branchName, orderID, index, qty, remarks);
          break;

        case (2): // edit an Order
          viewer.viewOrderDetails(branchName, orderID);

          System.out.println("Which item do you want to edit?:");
          index = sc.nextInt();
          System.out.println("Edit quantity:");
          qty = sc.nextInt();
          sc.nextLine();
          
          System.out.println("Edit remarks:");
          remarks = sc.nextLine();
          OrderCreationController.editOrder(branchName, orderID, index, qty, remarks);
          break;

        case (3): // delete an order
          viewer.viewOrderDetails(branchName, orderID);

          System.out.println("Which item do you want to remove?:");
          index = sc.nextInt();
          OrderCreationController.deleteFromOrder(branchName, orderID, index);
          break;
          
        case (4):
          viewer.viewOrderDetails(branchName, orderID);
          break;
        case (5): // checkout from the cart
            double total = viewer.viewOrderDetails(branchName, orderID);
            if (total<=0) break;
            
            boolean payment = false;
            System.out.println("Choose payment method: 1) Cash, 2) Credit Card, 3) PayNow, Any number to exit");
            index = sc.nextInt();
            switch (index) {
              case 1:
                payment = new CashController().processCashPayment(total);
                break;
              case 2:
                System.out.print("Enter card number: ");
                String cardNo = sc.next();
                payment = new CreditCardController(cardNo).processCardPayment(total);
                break;
              case 3:
                System.out.print("Enter phone number: ");
                String phoneNo = sc.next();
                payment = new PayNowController(phoneNo).processPayNowPayment(total);
                break;
            }

            checkout.checkOut(branchName, orderID, payment);
            choice = 6;
            break;
        }
      } while (choice >= 1 && choice <=5);
    }

    public static void viewOrderStatus(String branchName) {
      CustomerViewInterface viewer = new OrderViewController();
      System.out.println("Please input your OrderID to view your Order's status:  ");
      String orderID = sc.next();

      viewer.viewOrderStatus(branchName, orderID);
    }

    public static void collectOrder(String branchName) {
      OrderCollectionInterface collect = new OrderStatusController();
      System.out.println("Please input your OrderID to collect your order:  ");
      String orderID = sc.next();

      collect.collectOrder(branchName, orderID);
    }
  }

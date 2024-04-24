package boundary;

import java.util.Scanner;
import control.*;
import control.customer.CustomerViewInterface;
import entity.*;
import exception.AddStaffException;
import exception.BranchExistedException;
import exception.BranchNotExistException;
import exception.PromotionException;
import exception.StaffExistedException;
import exception.WrongPasswordException;

public class StaffMainPage {
  
  static Scanner sc = new Scanner(System.in);
  
  public static void StaffUI(User user){
    
    System.out.println("-----Staff-----");
    
    Staff staffUser = null;
    if (user instanceof Staff)
      staffUser = (Staff) user;
    
      String branchName = staffUser.getBranchName();
      String orderID;
      int choice;
    
      do{
      System.out.println("What action do you want to do:");
            System.out.println("1)View New Orders\n"
                + "2)View an Order\n"
                + "3)Update Order Status\n"
                + "4)Change Password\n"
                + "5)Log Out");
          
        choice = sc.nextInt(); 
                  
      switch (choice){
        case 1: 
          viewNewOrders(branchName); 
            break;  
        case 2: 
          System.out.println("Please input the order ID:");
          orderID = sc.next();
          viewAnOrder(branchName,orderID); 
            break;
        case 3: 
          System.out.println("Please input the order ID:");
          orderID = sc.next();
          updateOrderStatus(branchName, orderID);
          break;
        case 4: 
          //Change Password
          UserChangePassword.changePasswordUI(staffUser); 
          break;
        case 5:
          //Log out 
          LogOut.logoutUI(staffUser, Role.STAFF); 
          break;
      }
      
     }while(choice>0 && choice<=5);
  }

  public static void viewNewOrders(String branchName) {
    StaffViewInterface viewer = new OrderViewController();
    viewer.viewNewOrders(branchName);
  }

  // Case 2
  public static void viewAnOrder(String branchName, String orderID) {
    CustomerViewInterface viewer = new OrderViewController();
    viewer.viewOrderDetails(branchName, orderID);
  }

  // Case 3
  public static void updateOrderStatus(String branchName, String orderID) {
    OrderProcessingInterface process = new OrderStatusController();
    process.processOrder(branchName, orderID);
  }
}

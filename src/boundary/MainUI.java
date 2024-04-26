package boundary;

import java.util.*;


import entity.Company;
import exception.AddStaffException;
import exception.BranchExistedException;
import exception.BranchNotExistException;
import exception.PromotionException;
import exception.StaffExistedException;
import exception.WrongPasswordException;

public class MainUI {
	
	static Scanner sc = new Scanner(System.in);
	
public static void MainPageUI(){
		
	welcomeMessage();
    
    int choice;
    
    do {
      choice = roleChoice();
      
      switch(choice) {
        case 1:
          CustomerMainPage.CustomerMainPageUI();
          break;
        case 2:
          EmployeeLogin.employeeLoginUI();
          break;
        case 3:
          System.out.println("Bye!");
          break;
        default:
          System.out.println("Try again");
          break;
      }
    } while (choice!=3);
}

	
public static int roleChoice() {
	
	System.out.println("Enter your role");
	System.out.println("1: Customer\n2: Staff");
	int choice = sc.nextInt();
	
	return choice;
	
}

public static void welcomeMessage() {
	
	System.out.println("Welcome to 20-02 Fast Food Chain.");
	
}

}
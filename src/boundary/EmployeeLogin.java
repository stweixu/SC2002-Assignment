package boundary;

import java.util.*;


import entity.Company;
import entity.User;
import exception.AddStaffException;
import exception.BranchExistedException;
import exception.BranchNotExistException;
import exception.PromotionException;
import exception.StaffExistedException;
import exception.WrongPasswordException;
import control.AccountController; 
import control.CompanyController;
import entity.Admin;
import entity.Staff;


public class EmployeeLogin {
	
	static Scanner sc = new Scanner(System.in);
	
@SuppressWarnings("incomplete-switch")
public static void employeeLoginUI(){
	
	User user = null;
	
	do {
		
		String loginID = enterLoginID();
		String password = enterPassword();
		
		//Returns Admin or Staff object if ID and password matches
		user = checkValid(loginID, password);
		
		if (user == null) {
			System.out.println("Failed to login. Incorrect Login ID or Password.");
			System.out.println("1: Try again");
			System.out.println("2: Go back to main interface.");
			int choice = sc.nextInt();
			
			switch(choice) {
			case(1): 
				continue;
			case(2):
				MainUI.MainPageUI();
			}
		} 
	//END DO-WHILE
	} while (user == null);
	
	if (user instanceof Admin)
		AdminMainPage.AdminUI(user);
	
	if (user instanceof Staff) {
		Staff staffUser = (Staff) user; 
		
		switch(staffUser.getRole()) {
			case STAFF:
				StaffMainPage.StaffUI(staffUser);
				break;
			case MANAGER:
				ManagerMainPage.ManagerUI(staffUser);
		}
	}
}
	

public static String enterLoginID() {
	
	String loginID;
	System.out.println("Enter your Staff Login ID: ");
	loginID = sc.next();
	
	return loginID;
}

public static String enterPassword() {
	
	String password;
	System.out.println("Enter your password");
	password = sc.next();
	
	return password;
	
}

public static User checkValid(String loginID, String password) {
	
	User tempUser;
	
	try {
		tempUser = AccountController.checkExisting(loginID);
	} catch (Exception e) {
		//System.out.println(e.getMessage());
		return null;
	}
	
	try {
		AccountController.checkValid(tempUser, password);
	} catch (Exception e) {
		//System.out.println(e.getMessage());
		return null;
	}
	
	//Return Staff/Admin Object if password is correct
	return tempUser;
	
}

}

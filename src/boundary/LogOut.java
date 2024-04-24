package boundary;

import java.util.*;

import entity.Admin;
import entity.Company;
import entity.Role;
import entity.Staff;
import entity.User;
import exception.AddStaffException;
import exception.BranchExistedException;
import exception.BranchNotExistException;
import exception.PromotionException;
import exception.StaffExistedException;
import exception.WrongPasswordException;

public class LogOut {
	
	static Scanner sc = new Scanner(System.in);
	
public static void logoutUI(User user, Role role){
	
	System.out.println("Are you sure you want to log out?");
	System.out.println("1: No, return to my staff page.");
	System.out.println("2: Yes, log out.");
	
	int choice = sc.nextInt();
	
	if (choice == 1) {
		if (role.equals(Role.STAFF)) {
			StaffMainPage.StaffUI(user);
		}
		if (role.equals(Role.MANAGER)) {
			ManagerMainPage.ManagerUI(user);
		}
		if (role.equals(Role.ADMIN)) {
			AdminMainPage.AdminUI(user);
		}
		
	} else if (choice == 2) {
		MainUI.MainPageUI();
		}
	}
}

package boundary;

import java.util.Scanner;

import entity.Admin;
import entity.Company;
import entity.Staff;
import entity.User;
import exception.AddStaffException;
import exception.BranchExistedException;
import exception.BranchNotExistException;
import exception.PromotionException;
import exception.StaffExistedException;
import exception.WrongPasswordException;
import control.AccountController;
import control.PasswordController;

public class UserChangePassword {
	
	static Scanner sc = new Scanner(System.in);

	@SuppressWarnings("incomplete-switch")
	public static void changePasswordUI(User user){
		
		System.out.println("Enter your old password");
		String oldPassword = sc.next();
		
		System.out.println("Enter your new password");
		String newPassword = sc.next();
		
		try {
			PasswordController.changePassword(user, oldPassword, newPassword);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Returning to previous page...");
			return;
		}

		
		if (user instanceof Admin)
			AdminMainPage.AdminUI(user);
		
		if (user instanceof Staff) {
			Staff staffUser = (Staff) user; 
			
			switch(staffUser.getRole()) { //need to add getRole in StaffController class?
				case STAFF:
					StaffMainPage.StaffUI(staffUser);
					break;
				case MANAGER:
					ManagerMainPage.ManagerUI(staffUser);
			}
		}
		
	}

}

package boundary;

import java.util.*;


import control.AccountController;
import control.CompanyController;
import control.admin.*;
import entity.Branch;
import entity.Company;
import entity.Role;
import entity.Staff;
import entity.User;
import exception.*;


public class AdminMainPage {
	
	static Scanner sc = new Scanner(System.in);
	
	
public static void AdminUI(User user){
	
	System.out.println("-----Admin-----");
	int categoryChoice;
	
	do {
		categoryChoice = categoryChoice();
		
		if (categoryChoice != 4)
			System.out.println("\nWhat actions would you like to do?");
		
		switch(categoryChoice) {
	
		case(1):
			manpowerActions();
			break;
			
		case(2):
			branchActions();
			break;
			
		case(3):
			paymentActions();
			break;
		
		case(4):
			UserChangePassword.changePasswordUI(user);
			break;
			
		case(5):
			LogOut.logoutUI(user, Role.ADMIN);
			break;
		
		default:
			System.out.println("Input from 1 to 5 only");
		}
	} while (categoryChoice != 4);
	
}
	
public static int categoryChoice() {
	
	System.out.println("What category would you like to edit? (1-5 only)");
	System.out.println("1: Manpower-related");
	System.out.println("2: Branch-related");
	System.out.println("3: Payment-related");
	System.out.println("4: Change password");
	System.out.println("5: Nothing. Log out!");
	
	int choice = sc.nextInt();
	
	return choice;
	
}

public static void manpowerActions(){
	
	System.out.println("0: Display staff list");
	System.out.println("1: Add staff to a branch");
	System.out.println("2: Remove staff from a branch");
	System.out.println("3: Edit a staff's details");
	System.out.println("4: Transfer a staff to another branch");
	System.out.println("5: Promote a staff to manager");
	System.out.println("6: Return to admin category page");
	
	int manpowerAction = sc.nextInt();
	
	switch(manpowerAction) {
	case 0:
		displayStaff();
		break;
	case 1: 
		addStaff();
		break;
	case 2:
		removeStaff();
		break;
	case 3:
		editStaff();
		break;
	case 4:
		transferStaff();
		break;
	case 5:
		promoteStaff();
		break;
	case 6:
		return;
	}
	
	return;
	
}

public static void displayStaff() {
	
	System.out.println("Filter by (1-4 only)\n"
			+ "1: Branch\n"
			+ "2: Role\n"
			+ "3: Gender\n"
			+ "4: Age\n");
	
	int filterChoice = sc.nextInt();
	
	switch (filterChoice) {
	case 1:
		AdminController.displayStaffByBranch();
		break;
	case 2:
		AdminController.displayStaffByRole();
		break;
	case 3:
		AdminController.displayStaffByGender();
		break;
	case 4:
		AdminController.displayStaffByAge();
		break;
	}
	
}

public static void addStaff(){
	
	sc.nextLine();
	System.out.println("New Staff Username: ");
	String newName = sc.nextLine();
	
	String newLoginID;	
		System.out.println("New Staff Login ID: ");
		newLoginID = sc.nextLine();
		
	
	System.out.println("New Staff's role (1 or 2):\n"
			+ "1: Staff\n"
			+ "2: Manager");
	int roleChoice = sc.nextInt();
	Role staffRole = null;
	
	if (roleChoice == 1)
	{staffRole = Role.STAFF;}
	else if (roleChoice == 2)
	{staffRole = Role.MANAGER;}
	
	System.out.println("New Staff's age (integer):");
	int staffAge = sc.nextInt();
	
	System.out.println("New Staff's gender (M/F): ");
	String staffGender = sc.next();
	
	
	CompanyController.displayBranch();
	System.out.println("Staff branch assignment (enter the branch's name):");
	String branchKey = sc.next();
	
	Staff newStaff = new Staff(newName, newLoginID, staffRole, branchKey, staffAge, staffGender);

	try {
		AdminController.addStaff(newStaff, Company.getBranch().get(branchKey));		
	} catch (StaffExistedException e) {
		System.out.println(e.getMessage());
		System.out.println("Returning to previous page...");
		return;
	} catch (AddStaffException e) {
		System.out.println(e.getMessage());
		System.out.println("Returning to previous page...");
		return;
	}

	System.out.println("Staff successfully added.\n");
	
	return;
}

public static void removeStaff() {
	
	AdminController.displayStaffByBranch();
	System.out.println("\nEnter the staff login ID to be removed: ");
	String staffID = sc.next();
	User user;
	
	try {
		user = AccountController.checkExisting(staffID);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Returning to previous page...");
		return;
	}
	
	if (user instanceof Staff) {
		Staff staff = (Staff) user;
		AdminController.deleteStaff(staff);
	}
	
	System.out.println("Staff successfully deleted.\n");
	
	return;
}

public static void editStaff() {
	
	AdminController.displayStaffByBranch();
	System.out.println("\nEnter the staff login ID: ");
	String staffID = sc.next();
	User user;
	
	try {
		user = AccountController.checkExisting(staffID);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Returning to previous page...");
		return;
	}
	
	if (user instanceof Staff) {
		System.out.println("Edit staff particulars:\n"
				+ "1: Username\n"
				+ "2: Login ID\n"
				+ "3: Role\n");
		
		Staff staff = (Staff) user;
		
		int choice = sc.nextInt();
		switch(choice) {
		case 1: 
			System.out.println("Enter edited username: ");
			String editedName = sc.next();
			try {
				AdminController.editStaffId(staff, editedName);
			} catch (Exception e){
				System.out.println(e.getMessage());
				System.out.println("Returning to previous page...");
				return;
			}
			break;
			
		case 2:
			System.out.println("Enter edited login ID: ");
			String editedID = sc.next();
			try {
				AdminController.editStaffId(staff, editedID);
			}
			catch (Exception e){
				System.out.println(e.getMessage());
				System.out.println("Returning to previous page...");
				return;
			}
			break;
			
		case 3:
			System.out.println("Enter edited staff role (1 or 2):\n"
					+ "1: Staff\n"
					+ "2: Manager");
			int roleChoice = sc.nextInt();
			Role staffRole = null;
			
			if (roleChoice == 1)
				staffRole = Role.STAFF;
			if (roleChoice == 2)
				staffRole = Role.MANAGER;
			try {
				AdminController.editStaffRole(staff, staffRole);
			}
			catch (Exception e){
				System.out.println(e.getMessage());
				System.out.println("Returning to previous page...");
				return;
			}
			break;		
		}
	}
	
	System.out.println("Staff's info successfully edited\n");
	return;
}

public static void transferStaff() {
	
	AdminController.displayStaffByBranch();
	System.out.println("\nEnter staff ID to be transferred: ");
	String staffID = sc.next();
	User user;
	
	try {
		user = AccountController.checkExisting(staffID);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Returning to previous page...");
		return;
	}
	
	if (user instanceof Staff) {
		Staff staff = (Staff) user;
		
		CompanyController.displayBranch();
		System.out.println("Enter the branch name to be transferred to: ");
		String branchKey = sc.next();
		
		Branch branch = null;		
		try {
			branch = AdminController.getBranch(branchKey);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Returning to previous page...");
			return;
		}
		
		try {
			AdminController.transferStaff(staff, branchKey);
		} 		
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Returning to previous page...");
			return;
		}
	}
	
	System.out.println("Staff successfully transferred\n");
	
	return;
}

public static void promoteStaff(){
	
	AdminController.displayStaffByBranch();
	System.out.println("\nEnter staff ID to be promoted: ");
	String staffID = sc.next();
	User user;
	
	try {
		user = AccountController.checkExisting(staffID);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Returning to previous page...");
		return;
	}
	
	if (user instanceof Staff) {
		Staff staff = (Staff) user;
		
		try {
		AdminController.promoteStaff(staff);
		}  catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Returning to previous page...");
			return;
		}
	
		System.out.println("Promotion successful\n");
	}
}

public static void branchActions(){
	
	System.out.println("0: Display existing branch");
	System.out.println("1: Open a new branch");
	System.out.println("2: Close an existing branch");
	System.out.println("3: Return to admin category page");
	
	int branchAction = sc.nextInt();
	switch(branchAction) {
	case 0:
		CompanyController.displayBranch();
		break;
	case 1:
		openBranch();
		break;
	case 2:
		closeBranch();
		break;
	case 3:
		return;
	}
	
	return;			
}

public static void openBranch(){
	
	Scanner sc = new Scanner(System.in);
	
	System.out.println("New Branch name: ");
	String branchName = sc.nextLine();	

	
	System.out.println("New Branch location: ");
	String branchLoc = sc.nextLine();
	
	
	System.out.println("Staff quota (Integer): ");
	int staffQuota = sc.nextInt();
	
	Branch newBranch = new Branch(branchName, branchLoc, staffQuota);
	
	try {
		AdminController.openBranch(newBranch);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Returning to previous page...");
		return;
	}
	
	System.out.println("New branch successfully opened\n");
	
	return;
}

public static void closeBranch(){
	CompanyController.displayBranch();
	System.out.println("\nEnter the branch key to be closed.");
	String branchKey = sc.next();
	Branch branch = null;
	
	try {
		branch = AdminController.getBranch(branchKey);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Returning to previous page...");
		return;
	}
	try {
		AdminController.closeBranch(branch);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Returning to previous page...");
		return;
	}
	System.out.println("Branch successfully closed\n");
	return;
}

public static int paymentActions() {
	
	System.out.println("0: Display existing payment methods");
	System.out.println("1: Add a new payment method");
	System.out.println("2: Remove an existing payment method");
	System.out.println("3: Return to action page");
	
	int paymentAction = sc.nextInt();
	
/*	switch(paymentAction) {
 		case 0:
 			PaymentController.displayPaymentMethods();
 			break;
 		case 1:
 			PaymentController.addPaymentMethod();
 			break;
 		case 2:
 			PaymentController.removePaymentMethod();
 			break;
 		case 3:
 			return;
 		}
 		
 	return;
 */
	
	return paymentAction;
}

}

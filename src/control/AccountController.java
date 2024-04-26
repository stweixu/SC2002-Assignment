package control;

import entity.Branch;
import entity.Company;
import entity.User;
import exception.StaffNotExistException;
import exception.WrongPasswordException;

/*
 * AccountController checks for
 * correct login credentials
 * 
 */

public class AccountController {
	/**
	 * checkExisting() checks if the loginID exists in the company's databse
	 * 
	 * @param loginID The loginID to check against
	 * @return User object that matches the loginID.
	 */

	// Check if user account exists in the system
	public static User checkExisting(String loginID) throws StaffNotExistException {

		// Look for occurrence of loginID among the pool of admin in the company
		for (User user : Company.getAdmin()) {
			if (user.getUserId().equals(loginID)) {
				return user;
			}
		}

		// Look for occurrence of loginID among the pool of staffs in the company
		for (Branch branch : Company.getBranch().values()) {
			for (User user : branch.getStaffList()) {
				if (user.getUserId().equals(loginID)) {
					return user;
				}
			}
		}

		// if user not found
		throw new StaffNotExistException();
	}

	/**
	 * checkValid() checks if the user's password is valid
	 * 
	 * @param user     The user account to check
	 * @param password The password to verify
	 * @return boolean
	 */
	public static boolean checkValid(User user, String password) throws WrongPasswordException {
		if (user.getPassword().equals(password))
			return true;

		throw new WrongPasswordException();
	}

}

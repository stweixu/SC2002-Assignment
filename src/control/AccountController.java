package control;

import entity.Branch;
import entity.Company;
import entity.User;
import exception.StaffNotExistException;
import exception.WrongPasswordException;

public class AccountController {
	
	public static User checkExisting(String loginID) throws StaffNotExistException {
		
		for (User user: Company.getAdmin()) {
			if (user.getUserId().equals(loginID)) {
				return user;
			}
		}
		
		for (Branch branch: Company.getBranch().values()) {
			for (User user: branch.getStaffList()) {
				if (user.getUserId().equals(loginID)) {
					return user;
				}
			}
		}
		
		//if user not found
		throw new StaffNotExistException();
	}

    public static boolean checkValid(User user, String password) throws WrongPasswordException{
        if (user.getPassword().equals(password)) 
        	return true;
        
        throw new WrongPasswordException();
    }

}

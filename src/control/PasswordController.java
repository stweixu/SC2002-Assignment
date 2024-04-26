package control;

import entity.User;
import exception.WrongPasswordException;

/**
 * Controller class to manage and change password
 */

public class PasswordController {

	/**
	 * Change password if old password entered by user is correct
	 * @param user
	 * @param oldPassword
	 * @param newPassword
	 * @return true if password is successfully changed
	 * @throws WrongPasswordException
	 */
	
    public static boolean changePassword(User user, String oldPassword, String newPassword) throws WrongPasswordException{
        if (AccountController.checkValid(user, oldPassword)){    // Check if user exists and if old password matches    
            user.setPassword(newPassword);                        // If yes, allow the change
            return true;
        } else {
            throw new WrongPasswordException();                // Else block the change by throwing an exception
        }
    }

}

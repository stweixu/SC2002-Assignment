package control;

import entity.User;
import exception.WrongPasswordException;

public class PasswordController {
    /**
     * changePassword() is to print out the menu items in a specific branch
     * @param user The user to check against the database.
     * @param oldPassword The old password to check against the database in String.
     * @param newPassword The new password to set for the user in String.
     * @return void.
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

package control;

import entity.User;
import exception.WrongPasswordException;

public class PasswordController {

    public static boolean changePassword(User user, String oldPassword, String newPassword) throws WrongPasswordException{
        if (AccountController.checkValid(user, oldPassword)){    // Check if user exists and if old password matches    
            user.setPassword(newPassword);                        // If yes, allow the change
            return true;
        } else {
            throw new WrongPasswordException();                // Else block the change by throwing an exception
        }
    }

}

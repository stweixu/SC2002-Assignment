package control;

import entity.User;
import exception.WrongPasswordException;

public class PasswordController {

    public static boolean changePassword(User user, String oldPassword, String newPassword) throws WrongPasswordException{
        if (AccountController.checkValid(user, oldPassword)){
            user.setPassword(newPassword);
            return true;
        } else {
            throw new WrongPasswordException();
        }
    }

}

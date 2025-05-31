package untilDown.com.Controllers;

import untilDown.com.Main;
import untilDown.com.Models.App;
import untilDown.com.Models.Result;
import untilDown.com.Models.User;
import untilDown.com.Views.SignupMenuView;

import java.util.regex.Pattern;

public class SignupMenuController {
    private SignupMenuView view;

    public void setView(SignupMenuView view) {
        this.view = view;
    }

    public String handleSignup(String username, String password, String confirmPassword, String answer) {
        Result result;

        if (!(result = checkUsername(username)).success ||
            !(result = checkPassword(password, confirmPassword)).success) {
            return result.message;
        }

        User user = new User(username, password, answer, true);
        App.getApp().addUser(user);
        App.getApp().setLoggedInUser(user);
        Main.getMain().navigateToLoginMenu();
        return "";
    }


    public Result checkUsername(String username) {
        User user = App.getApp().getUserByUsername(username);
        if (user != null)
            return new Result(false, "Username is already taken!");
        return new Result(true, "");
    }

    public Result checkPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return new Result(false, "Password and confirm password do not match");
        }

        if (password.length() < 8)
            return new Result(false, "Password must be at least 8 characters");

        boolean state = Pattern.compile("[A-Z]").matcher(password).find()
                    && Pattern.compile("\\d").matcher(password).find()
                    && Pattern.compile("[_()*&%$#@]").matcher(password).find();

        if (!state)
            return new Result(false, "Password must contains at least 1 upperCase, 1 digit, and 1 of _()*&%$#@");

        return new Result(true, "");
    }
}

package untilDown.com.Controllers;

import untilDown.com.Main;
import untilDown.com.Models.App;
import untilDown.com.Models.Result;
import untilDown.com.Models.User;
import untilDown.com.Views.LoginMenuView;
import untilDown.com.Views.SignupMenuView;

import java.util.regex.Pattern;

public class LoginMenuController {
    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public String handleLogin(String username, String password) {
        Result result;

        if (!(result = checkUsernameExist(username)).success) {
            return result.message;
        }

        User user = App.getApp().getUserByUsername(username);
        if (!isPasswordCorrect(user, password))
            return "Password Incorrect";

        App.getApp().setLoggedInUser(user);
        Main.getMain().navigateToMainMenu();
        return "";
    }


    public Result checkUsernameExist(String username) {
        User user = App.getApp().getUserByUsername(username);
        if (user == null) return new Result(false, "User with this username does not exist");
        return new Result(true, "");
    }

    public boolean isPasswordCorrect(User user, String password) {
        return user.getPassword().equals(password);
    }

    public Result checkPassword(String password) {
        if (password.length() < 8)
            return new Result(false, "Password must be at least 8 characters");

        boolean state = Pattern.compile("[A-Z]").matcher(password).find()
            && Pattern.compile("\\d").matcher(password).find()
            && Pattern.compile("[_()*&%$#@]").matcher(password).find();

        if (!state)
            return new Result(false, "Password must contains at least 1 upperCase, 1 digit, and 1 of _()*&%$#@");

        return new Result(true, "");
    }


    public String handleSetNewPass(String username, String password, String answer) {
        Result result;

        if (!(result = checkUsernameExist(username)).success
            || !(result = checkPassword(password)).success) {
            return result.message;
        }

        User user = App.getApp().getUserByUsername(username);
        if (!user.getAnswer().equals(answer))
            return "Your answer to question is incorrect!";

        user.setPassword(password);
        Main.getMain().navigateToLoginMenu();
        return "";
    }
}

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
        if (!user.getPassword().equals(password))
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

}

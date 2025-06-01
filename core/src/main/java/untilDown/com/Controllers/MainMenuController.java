package untilDown.com.Controllers;
import untilDown.com.Main;
import untilDown.com.Models.App;
import untilDown.com.Models.Result;
import untilDown.com.Models.User;
import untilDown.com.Views.MainMenuView;
import untilDown.com.Views.ProfileMenuView;

import java.util.regex.Pattern;


public class MainMenuController {
    private MainMenuView mainView;
    private ProfileMenuView profileView;

    public void setView(MainMenuView view) {
        this.mainView = view;
    }

    public void setProfileView(ProfileMenuView view) {
        this.profileView = view;
    }

    public void logoutUser() {
        App.getApp().setLoggedInUser(null);
        Main.getMain().navigateToLoginMenu();
    }

    public String saveProfile() {
        User user = App.getApp().getLoggedInUser();

        String newUsername = profileView.getChangeUserNameField().getText();
        String newPassword = profileView.getChangePasswordField().getText();

        if (newUsername.isEmpty()) {
            return "Username field cannot be empty!";
        } else if (!user.isGuest() && newPassword.isEmpty()) {
            return "Password field cannot be empty!";
        }

        Result result;
        if (!(result = checkNewUsername(newUsername, user)).success ||
            (!user.isGuest() && !(result = checkNewPassword(newPassword)).success)) {
            return result.message;
        }

        user.setUsername(newUsername);
        user.setPassword(newPassword);
        Main.getMain().navigateToMainMenu();
        return "";
    }

    public Result checkNewUsername(String newUsername, User loggedInUser) {
        User other = App.getApp().getUserByUsername(newUsername);
        if (other != null && other != loggedInUser) {
            return new Result(false, "Username "+ other.getUsername() + " is already taken!");
        }
        return new Result(true, "");
    }

    public Result checkNewPassword(String password) {
        if (password.length() < 8)
            return new Result(false, "Password must be at least 8 characters");

        boolean state = Pattern.compile("[A-Z]").matcher(password).find()
            && Pattern.compile("\\d").matcher(password).find()
            && Pattern.compile("[_()*&%$#@]").matcher(password).find();

        if (!state)
            return new Result(false, "Password must contains at least 1 upperCase, 1 digit, and 1 of _()*&%$#@");

        return new Result(true, "");
    }

    public void deleteAccount() {

    }
}

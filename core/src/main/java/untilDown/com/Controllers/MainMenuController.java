package untilDown.com.Controllers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import untilDown.com.Main;
import untilDown.com.Models.App;
import untilDown.com.Models.Result;
import untilDown.com.Models.User;
import untilDown.com.Models.avatar.Avatar;
import untilDown.com.Models.avatar.AvatarCustom;
import untilDown.com.Views.MainMenuView;
import untilDown.com.Views.ProfileMenuView;

import javax.swing.*;
import java.awt.*;
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
        User user = App.getApp().getLoggedInUser();
        if (user.isGuest()) {
            App.getApp().setLoggedInUser(null);
        } else {
            App.getApp().deleteUser(user);
            App.getApp().setLoggedInUser(null);
        }

        Main.getMain().navigateToLoginMenu();
    }

    public void handleChangeAvatar() {
        User user = App.getApp().getLoggedInUser();

        FileDialog fileDialog = new FileDialog((Frame) null, "Select Image");
        fileDialog.setMode(FileDialog.LOAD);
        fileDialog.setVisible(true);

        if (fileDialog.getFile() == null) {
            return;
        }

        String filePath = fileDialog.getDirectory() + fileDialog.getFile();

        FileHandle target = Gdx.files.local("data/avatars/user_avatar_" + (int)(Math.random()*100000)+ ".png");
        FileHandle source = new FileHandle(filePath);
        source.copyTo(target);

        user.setAvatar(new AvatarCustom(target.path()));
    }
}

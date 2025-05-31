package untilDown.com.Controllers;
import untilDown.com.Main;
import untilDown.com.Models.App;
import untilDown.com.Views.MainMenuView;


public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void logoutUser() {
        App.getApp().setLoggedInUser(null);
        Main.getMain().navigateToLoginMenu();
    }
}

package untilDown.com.Controllers;

import untilDown.com.Main;
import untilDown.com.Models.GameAssetManager;
import untilDown.com.Views.MainMenuView;
import untilDown.com.Views.PreGameMenuView;

public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButton() {
        if (view != null) { // only for better managing Exceptions

            // TODO : change to get users and checks the field
            if (view.getPlayButton().isChecked() && view.getTextField().getText().equals("mir")) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getManager().getSkin()));
            }
        }
    }

}

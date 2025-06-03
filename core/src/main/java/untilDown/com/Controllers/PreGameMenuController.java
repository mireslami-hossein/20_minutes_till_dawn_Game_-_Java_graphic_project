package untilDown.com.Controllers;

import untilDown.com.Main;
import untilDown.com.Models.GameAssetManager;
import untilDown.com.Models.Setting;
import untilDown.com.Views.GameView;
import untilDown.com.Views.PreGameMenuView;

public class PreGameMenuController {
    private PreGameMenuView view;

    public void setView(PreGameMenuView view) {
        this.view = view;

    }

    public void handleStartGame() {
        if (view != null) {
            if (view.getPlayButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getManager().getSkin()));
            }
        }
    }
}

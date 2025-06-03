package untilDown.com.Controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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

    public TextButton getPlayButton() {
        TextButton playButton = new TextButton("Play", GameAssetManager.getManager().getSkin());
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Setting.durationOfGame = view.getSelectGameTime();
                Main.getMain().navigateToGame();
            }
        });
        return playButton;
    }

    public TextButton getBackButton() {
        TextButton backButton = new TextButton("Back", GameAssetManager.getManager().getSkin());
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().navigateToMainMenu();
            }
        });
        return backButton;
    }
}

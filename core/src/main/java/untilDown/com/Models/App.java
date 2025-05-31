package untilDown.com.Models;

import com.badlogic.gdx.Gdx;
import untilDown.com.Controllers.SignupMenuController;

import java.util.ArrayList;

public class App {
    private static App app;
    private final String gameTitle = "20 Minutes Till Dawn";



    private ArrayList<User> users = new ArrayList<>();

    public static App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public float getWidth() {
        return Gdx.graphics.getWidth();
    }

    public float getHeight() {
        return Gdx.graphics.getHeight();
    }
}

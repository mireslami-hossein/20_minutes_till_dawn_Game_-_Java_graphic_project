package untilDown.com.Models;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

public class App {
    private static App app;

    private final String gameTitle = "20 Minutes Till Dawn";
    public static int fieldWidth = 600;

    private ArrayList<User> users = new ArrayList<>();

    private User loggedInUser = null;


    public static App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }

    // Constants
    public String getGameTitle() {
        return gameTitle;
    }

    public float getWidth() {
        return Gdx.graphics.getWidth();
    }

    public float getHeight() {
        return Gdx.graphics.getHeight();
    }

    // Users
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}

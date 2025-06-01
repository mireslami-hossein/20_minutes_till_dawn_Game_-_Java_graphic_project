package untilDown.com.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

public class App {
    private static App app;

    private final String gameTitle = "20 Minutes Till Dawn";
    public static int fieldWidth = 450;

    private ArrayList<User> users = new ArrayList<>();
    private User loggedInUser = null;

    private boolean autoReloadEnabled = true;

    private App() {
        loadUsers();
    }

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

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void saveUsers() {
        Json json = new Json();
        String jsonString = json.toJson(users);
        FileHandle file = Gdx.files.local("data/users.json");
        file.writeString(jsonString, false);
    }

    public void loadUsers() {
        FileHandle file = Gdx.files.local("data/users.json");

        if (file.exists() && !file.readString().isEmpty()) {
            Json json = new Json();
            Array<User> loadedUsers = json.fromJson(Array.class, User.class, file);
            for (User user : loadedUsers) {
                users.add(user);
            }
        }
    }

    // auto reload
    public boolean isAutoReloadEnabled() {
        return autoReloadEnabled;
    }

    public void setAutoReloadEnabled(boolean autoReloadEnabled) {
        this.autoReloadEnabled = autoReloadEnabled;
    }

    public void deleteUser(User user) {
        users.remove(user);
    }
}

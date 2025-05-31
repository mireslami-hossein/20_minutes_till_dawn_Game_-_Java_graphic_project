package untilDown.com.Models;

import com.badlogic.gdx.Input;

import java.util.HashMap;

public class GameKeysManager {
    private static GameKeysManager instance;

    private int goUp = Input.Keys.W;
    private int goDown = Input.Keys.S;
    private int goLeft = Input.Keys.A;
    private int goRight = Input.Keys.D;

    private int reload = Input.Keys.R;

    private int autoAim = Input.Keys.SPACE;
    private int shoot = Input.Buttons.LEFT;

    private GameKeysManager() {}

    public static GameKeysManager getManager() {
        if (instance == null) {
            instance = new GameKeysManager();
        }
        return instance;
    }

    public HashMap<String, Integer> getGameKeys() {
        HashMap<String, Integer> gameKeys = new HashMap<>();

        gameKeys.put("goUp", goUp);
        gameKeys.put("goDown", goDown);
        gameKeys.put("goLeft", goLeft);
        gameKeys.put("goRight", goRight);

        gameKeys.put("reload", reload);
        gameKeys.put("autoAim", autoAim);
        gameKeys.put("shoot" , shoot);

        return gameKeys;
    }

    public String getKeyName(int key) {
        String keyName = Input.Keys.toString(key);
        if (keyName.equals("Unknown")) {
            switch (key) {
                case 0:
                    keyName = "Left Mouse";
                    break;
                case 1:
                    keyName = "Right Mouse";
                    break;
                case 2:
                    keyName = "Middle Mouse";
                    break;
            }
        }

        return keyName;
    }
}

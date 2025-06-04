package untilDown.com.Models;

import com.badlogic.gdx.Input;

import java.util.HashMap;

public class GameKeysManager {
    private static GameKeysManager instance;

    private HashMap<String, Integer> keys = new HashMap<>();

    private GameKeysManager() {
        keys.put("goUp", Input.Keys.W);
        keys.put("goDown", Input.Keys.S);
        keys.put("goLeft", Input.Keys.A);
        keys.put("goRight", Input.Keys.D);
        keys.put("reload", Input.Keys.R);
        keys.put("autoAim", Input.Keys.SPACE);

        keys.put("shoot", -100 - Input.Buttons.LEFT); // Mouse buttons are -100 : Left, -101: Right, -102: Middle
    }

    public static GameKeysManager getManager() {
        if (instance == null) {
            instance = new GameKeysManager();
        }
        return instance;
    }

    public HashMap<String, Integer> getGameKeys() {
        return keys;
    }

    public String getKeyName(int key) {
        String keyName;
        if (key <= -100) {
            switch (key) {
                case -100:
                    keyName = "Left Mouse";
                    break;
                case -101:
                    keyName = "Right Mouse";
                    break;
                case -102:
                    keyName = "Middle Mouse";
                    break;
                default:
                    keyName = "UNKNOWN";
            }
        } else {
            keyName = Input.Keys.toString(key);
        }
        return keyName;
    }

    public void setKey(int keyOld, int keyNew) {
        for (String keyType : keys.keySet()) {
            if (keys.get(keyType) == keyOld) {
                keys.put (keyType, keyNew);
            }
        }
    }

    public int getKey(String key) {
        int keyCode = keys.get(key);
        if (keyCode < 0) {
            return -(100 + keyCode);
        }
        return keyCode;
    }
}

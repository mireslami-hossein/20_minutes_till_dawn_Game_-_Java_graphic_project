package untilDown.com.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager manager = null;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

    // player1
    private String[] player1_images;
    private final Texture[] player1_texture = new Texture[player1_images.length];
    private Animation<Texture> player1_animation;

    // gun
    private String smg = "guns/SMGStill/SMGStill.png";
    private Texture smgTexture = new Texture(Gdx.files.internal(smg));

    private String bullet = "guns/bullet.png";

    // bullet


    private GameAssetManager() {
        loadTextures();
    }

    public static GameAssetManager getManager() {
        if (manager == null) manager = new GameAssetManager();
        return manager;
    }

    public Skin getSkin() {
        return skin;
    }

    private void loadTextures() {
        player1_images = new String[6];
        for (int i = 0; i < player1_images.length; i++) {
            player1_images[i] = "player/player1/Idle_" + i + ".png";
        }

        for (int i = 0; i < player1_texture.length; i++) {
            player1_texture[i] = new Texture(Gdx.files.internal(player1_images[i]));
        }
        player1_animation = new Animation<>(0.1f, player1_texture);
    }

    public Animation<Texture> getPlayer1_animation() {
        return player1_animation;
    }

    public Texture getPlayer1_first_texture() {
        return player1_texture[0];
    }
}

package untilDown.com.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import untilDown.com.Main;
import untilDown.com.Models.Bullet;

import java.util.ArrayList;

public class WorldController {
    private PlayerController playerController;
    private Texture backgroundTexture;
    private float backgroundX = 0;
    private float backgroundY = 0;

    private static ArrayList<Bullet> enemyBullets = new ArrayList<>();

    public WorldController(PlayerController playerController) {
        this.backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        this.playerController = playerController;
    }


    public void updateGame() {
        Main.getBatch().draw(backgroundTexture, backgroundX, backgroundY);
    }

    public static void addBullet(Bullet bullet) {
        enemyBullets.add(bullet);
    }
}

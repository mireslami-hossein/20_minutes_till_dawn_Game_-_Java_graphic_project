package untilDown.com.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    private Texture playerTexture = GameAssetManager.getManager().getPlayer1_first_texture();

    private Sprite playerSprite = new Sprite(playerTexture);
    private float posX = 0;
    private float posY = 0;
    private float playerHealth = 100;


    private CollisionRect rect ; // bound of collision for player (a rectangle)
    private float time = 0;
    private float speed = 5;

    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;

    // current Game data
    private int currentGameKills;
    private int currentGameScore;
    private int currentGameTimeLived;


    public Player() {
        playerSprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        playerSprite.setSize(playerSprite.getWidth() * 3, playerSprite.getHeight() * 3);
        rect = new CollisionRect((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2,
            playerSprite.getWidth() * 3, playerSprite.getHeight() * 3);
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public void addToPosition(float x, float y) {
        this.posX += x;
        this.posY += y;
    }

    public float getSpeed() {
        return speed;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }
}

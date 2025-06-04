package untilDown.com.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import untilDown.com.Models.gun.PlayerGun;
import untilDown.com.Models.hero.Hero;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    private CollisionRect rect ; // bound of collision for player (a rectangle)
    private float time = 0;

    public enum PlayerState {
        Idle, Running
    }
    private PlayerState playerState = PlayerState.Idle;


    // current Game data
    private int currentGameKills;
    private int currentGameScore;
    private int currentGameTimeLived;

    private Hero hero;
    private PlayerGun gun;
    private ArrayList<ActiveBuff> activeBuffs = new ArrayList<>();

    // In Game
    private Sprite playerSprite;
    private Texture playerTexture;

    private CollisionRect collisionRect;
    private Vector2 playerPosition = new Vector2((float)Gdx.graphics.getWidth() / 2,(float)Gdx.graphics.getHeight() / 2);


    public Player() {
        this.hero = new Hero(Setting.heroSelected);
        this.gun = new PlayerGun(Setting.gunSelcected);

        playerTexture = new Texture(Gdx.files.internal(hero.getType().getPath()));
        playerSprite = new Sprite(playerTexture);
        playerSprite.setPosition(playerPosition.x, playerPosition.y);
    }

//    public Sprite getPlayerSprite() {
//        return playerSprite;
//    }
//
//    public boolean isPlayerIdle() {
//        return isPlayerIdle;
//    }
//
//    public void addToPosition(float x, float y) {
//        this.posX += x;
//        this.posY += y;
//    }
//
//    public float getSpeed() {
//        return speed;
//    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public Vector2 getPosition() {
        return playerPosition;
    }

    public void changePlayerPosition(float x, float y) {
        playerPosition.add(x, y);
    }

    public int getSpeed() {
        return hero.getType().getSpeed();
    }
}

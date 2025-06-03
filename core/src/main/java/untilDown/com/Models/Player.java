package untilDown.com.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import untilDown.com.Models.gun.PlayerGun;
import untilDown.com.Models.hero.Hero;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    private float posX = 0;
    private float posY = 0;

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
    private CollisionRect collisionRect;

    public Player() {
        this.hero = new Hero(Setting.heroSelected);
        this.gun = new PlayerGun(Setting.gunSelcected);

        System.out.println(this.gun);
        System.out.println(this.hero);
//        playerSprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
//        playerSprite.setSize(playerSprite.getWidth() * 3, playerSprite.getHeight() * 3);
//        rect = new CollisionRect((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2,
//            playerSprite.getWidth() * 3, playerSprite.getHeight() * 3);
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

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }
}

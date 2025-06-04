package untilDown.com.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import untilDown.com.Models.gun.GunType;
import untilDown.com.Models.gun.PlayerGun;
import untilDown.com.Models.hero.Hero;
import untilDown.com.Models.hero.HeroType;

import java.util.ArrayList;

public class Player {
    private float time = 0;

    public enum PlayerState {
        Idle, Walking, Running,
    }
    private PlayerState playerState = PlayerState.Idle;


    // current Game data
    private int currentGameKills = 0;
    private int currentGameXP = 0;

    private Hero hero;
    private PlayerGun gun;
    private ArrayList<ActiveBuff> activeBuffs = new ArrayList<>();

    // In Game
    private Sprite playerSprite;
    private Texture playerTexture;
    public enum Direction {
        Left, Right
    }
    public Direction direction = Direction.Right;

    private Vector2 playerPosition = new Vector2((float)Gdx.graphics.getWidth() / 2,(float)Gdx.graphics.getHeight() / 2);


    public Player() {
        this.hero = new Hero(Setting.heroSelected);
        this.gun = new PlayerGun(Setting.gunSelected);

        playerTexture = new Texture(Gdx.files.internal(hero.getType().getPlayerPath()));
        playerSprite = new Sprite(playerTexture);
        playerSprite.setScale(2f);
        playerSprite.setPosition(playerPosition.x, playerPosition.y);
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

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
        return hero.getSpeed();
    }

    public int getPlayerLevel() {
        int level = 0;
        int xp = this.currentGameXP;
        while(xp >= 0) {
            xp -= 20 * (level + 1);
            level++;
        }
        return level;
    }

    public int getMaxXPForNextLevel(int level) {
        int xp = 0;
        for (int i = 1; i <= level; i++) {
            xp += (i) * 20;
        }
        return xp;
    }

    public int getCurrentGameXP() {
        return currentGameXP;
    }

    public void addCurrentGameXP(int x) {
        currentGameXP += x;
    }

    public HeroType getHeroType() {
        return hero.getType();
    }

    public GunType getGunType() {
        return gun.getType();
    }

    public int getCurrentGameKills() {
        return currentGameKills;
    }

    public Hero getHero() {
        return hero;
    }

    public PlayerGun getGun() {
        return gun;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean collidesWith(Sprite sprite) {
        return playerPosition.x < sprite.getX() && playerPosition.x + playerSprite.getWidth() > sprite.getX()
            && playerPosition.y < sprite.getY() && playerPosition.y + playerSprite.getHeight() > sprite.getY() ;
    }

    public void addToHp(int amount) {
        hero.changeHP(amount);
    }
}

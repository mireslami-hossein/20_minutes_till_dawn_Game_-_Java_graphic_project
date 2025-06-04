package untilDown.com.Models.hero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum HeroType {
    Shana(4, 4, "Shana Hero have 4 HP and 4 Speed!\nHmm, average maybe!"),
    Diamond(7, 1, "Diamond Hero have 7 HP and 1 Speed!\nOh! Too slow but hardy!"),
    Scarlet(3, 5, "Scarlet Hero have 3 HP and 5 Speed!\nFast enough to flea from enemies!"),
    Lilith(5, 3, "Lilith Hero have 5 HP and 3 Speed!\nHmm, a bit slow but good!"),
    Dasher(2, 10, "Dasher have 2 HP and 10 Speed!\nShe Dashes from enemies!!");

    private int maxHp;
    private int speed;
    private String hintText;


    HeroType(int maxHp, int speed, String hintText) {
        this.maxHp = maxHp;
        this.speed = speed;
        this.hintText = hintText;
    }

    public String getPortraitPath() {
        return "heroes/portraits/" + this.name() + "_Portrait.png";
    }


    public int getSpeed() {
        return speed;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public String getHintText() {
        return hintText;
    }

    public String getPlayerPath() {
        return "heroes/" + this.name() + "/idle/Idle_0.png";
    }

    public Animation<TextureRegion> getIdleAnimation() {
        return getAnimation("idle", 6, 0.1f);
    }

    public Animation<TextureRegion> getRunAnimation() {
        return getAnimation("run", 4, 0.1f);
    }

    public Animation<TextureRegion> getWalkAnimation() {
        if (!Gdx.files.internal("heroes/" + this.name() + "/walk/spritesheet.png").exists()) {
            return getRunAnimation();
        }
        return getAnimation("walk", 8, 0.1f);
    }

    public Animation<TextureRegion> getAnimation(String type, int num, float duration) {
        Texture player_spriteSheet = new Texture(Gdx.files.internal("heroes/" + this.name() + "/"+type+"/spritesheet.png"));
        TextureRegion[][] splitSpritesheet = TextureRegion.split(player_spriteSheet, player_spriteSheet.getWidth()/num, player_spriteSheet.getHeight());

        TextureRegion[] runFrames = new TextureRegion[6];
        for (int i = 0; i < num; i++) {
            runFrames[i] = splitSpritesheet[0][i];
        }

        return (new Animation<>(duration, runFrames));
    }
}

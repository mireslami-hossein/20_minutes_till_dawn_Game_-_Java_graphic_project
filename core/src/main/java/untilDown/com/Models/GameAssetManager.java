package untilDown.com.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager manager = null;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

    public enum MusicOfGame {
        Music_1, Music_2, Music_3;

        public String getMusicAddress() {
            return "musics/music_" + (ordinal() + 1);
        }
    }
    private MusicOfGame music = MusicOfGame.Music_1;
    private float musicVolume = 50;
    private boolean sfxEnabled = true;
    private boolean whiteBlackEnabled = false;

    private Animation<TextureRegion> treeAnimation;
    private Animation<TextureRegion> tentacleAnimation;
    private Animation<TextureRegion> eyeBatAnimation;
    private Texture elder;

    private GameAssetManager() {
        loadAnimations();
    }

    public static GameAssetManager getManager() {
        if (manager == null) manager = new GameAssetManager();
        return manager;
    }

    public Skin getSkin() {
        return skin;
    }


    public void loadAnimations() {
        loadTree();
        loadTentacle();
        loadEyeBat();
        elder = new Texture(Gdx.files.internal("enemy/elder/Elder.png"));
    }

    public void loadTree() {
        Texture treeSprite = new Texture(Gdx.files.internal("enemy/tree/spritesheet.png"));
        TextureRegion[][] region = TextureRegion.split(treeSprite, treeSprite.getWidth()/3, treeSprite.getHeight());
        TextureRegion[] runFrames = new TextureRegion[region.length];
        for (int i = 0; i < region.length; i++) {
            runFrames[i] = region[i][0];
        }
        treeAnimation = new Animation<>(1f, runFrames);
        treeAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    public void loadTentacle() {
        Texture tentacleSprite = new Texture(Gdx.files.internal("enemy/tentacle/spritesheet.png"));
        TextureRegion[][] region = TextureRegion.split(tentacleSprite, tentacleSprite.getWidth()/4, tentacleSprite.getHeight());
        TextureRegion[] runFrames = new TextureRegion[region.length];
        for (int i = 0; i < region.length; i++) {
            runFrames[i] = region[i][0];
        }
        tentacleAnimation = new Animation<>(1f, runFrames);
        tentacleAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    public void loadEyeBat() {
        Texture eyeBatSprite = new Texture(Gdx.files.internal("enemy/eyeBat/spritesheet.png"));
        TextureRegion[][] region = TextureRegion.split(eyeBatSprite, eyeBatSprite.getWidth()/4, eyeBatSprite.getHeight());
        TextureRegion[] runFrames = new TextureRegion[region.length];
        for (int i = 0; i < region.length; i++) {
            runFrames[i] = region[i][0];
        }
        eyeBatAnimation = new Animation<>(1f, runFrames);
        eyeBatAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Animation<TextureRegion> getTreeAnimation() {
        return treeAnimation;
    }

    public Animation<TextureRegion> getTentacleAnimation() {
        return tentacleAnimation;
    }

    public Animation<TextureRegion> getEyeBatAnimation() {
        return eyeBatAnimation;
    }

    public Texture getElder() {
        return elder;
    }

    // Music
    public MusicOfGame getMusic() {
        return music;
    }

    public void setMusic(int selected) {
        music = MusicOfGame.values()[selected];
        //TODO : play music
    }

    public void setMusicVolume(float volume) {
        this.musicVolume = volume;
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    // booleans
    public boolean isSfxEnabled() {
        return sfxEnabled;
    }

    public void setSfxEnabled(boolean sfxEnabled) {
        this.sfxEnabled = sfxEnabled;
    }

    public boolean isWhiteBlackEnabled() {
        return whiteBlackEnabled;
    }

    public void setWhiteBlackEnabled(boolean whiteBlackEnabled) {
        this.whiteBlackEnabled = whiteBlackEnabled;
    }


    public Texture getBullet() {
        return (new Texture(Gdx.files.internal("guns/bullet.png")));
    }
}

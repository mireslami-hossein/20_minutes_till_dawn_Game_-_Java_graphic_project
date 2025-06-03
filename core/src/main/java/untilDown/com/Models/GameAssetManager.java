package untilDown.com.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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

    // player1
    private String[] player1_images;
    private Texture[] player1_texture;
    private Animation<Texture> player1_animation;

    // gun
//    private String smg = "guns/SMGStill/SMGStill.png";
//    private Texture smgTexture = new Texture(Gdx.files.internal(smg));

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

        player1_texture = new Texture[6];
        for (int i = 0; i < player1_images.length; i++) {
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
}

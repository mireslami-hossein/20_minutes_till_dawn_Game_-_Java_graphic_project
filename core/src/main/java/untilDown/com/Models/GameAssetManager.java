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

    private GameAssetManager() {
    }

    public static GameAssetManager getManager() {
        if (manager == null) manager = new GameAssetManager();
        return manager;
    }

    public Skin getSkin() {
        return skin;
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

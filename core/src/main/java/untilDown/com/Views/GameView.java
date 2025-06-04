package untilDown.com.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import untilDown.com.Controllers.GameController;
import untilDown.com.Main;
import untilDown.com.Models.HeartAnimationActor;

import java.util.ArrayList;
import java.util.List;

// by setting InputProcessor to GameView we can handle each Key doing easily!
public class GameView implements Screen, InputProcessor {

    private GameController controller;
    private Stage stage;

    private Texture background;

    private int heartSize = 32;

    private Texture[] HPHeart;
    private Texture HPHeartEmptied;
    private List<HeartAnimationActor> heartActors = new ArrayList<>();
    private List<Image> emptyHeartImages = new ArrayList<>();


    private Texture heroTexture;
    private Label levelLabel;
    private Label killsLabel;
    private ProgressBar xpBar;

    private Texture gun;
    private Label gunAmmo;

    private Label timeLabel;

    private ScreenViewport gameViewport;
    private ScreenViewport UIViewport;


    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        levelLabel = new Label("", skin);
        killsLabel = new Label("", skin);

        xpBar = new ProgressBar(0, 100, 1, false, skin);

        gunAmmo = new Label("", skin);
        timeLabel = new Label("", skin);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);

        background = new Texture(Gdx.files.internal("background.png"));

        controller.setCamera();

        // IMPORTANT : view port and camera should be set together
        gameViewport = new ScreenViewport(controller.getCamera());
        UIViewport = new ScreenViewport();
        stage = new Stage(UIViewport);

        // heart
        HPHeart = new Texture[3];
        for (int i = 0; i < HPHeart.length; i++) {
            HPHeart[i] = new Texture(Gdx.files.internal("heart/HeartAnimation_" + i + ".png"));
        }

        // hearts
        int x = 10;
        for (int i = 0; i < 10; i++) {
            HeartAnimationActor heart = new HeartAnimationActor(HPHeart, x, UIViewport.getScreenHeight() - 50, heartSize, heartSize, 0.3f);
            heart.setVisible(false);
            stage.addActor(heart);
            heartActors.add(heart);

            Image emptyHeart = new Image(new TextureRegionDrawable(new TextureRegion(HPHeartEmptied)));
            emptyHeart.setSize(heartSize, heartSize);
            emptyHeart.setPosition(x, UIViewport.getScreenHeight() - 50);
            emptyHeart.setVisible(false);
            stage.addActor(emptyHeart);
            emptyHeartImages.add(emptyHeart);

            x += 32;
        }


        heroTexture = new Texture(controller.getPlayer().getHeroType().getPortraitPath());

        gun = new Texture(controller.getPlayer().getGunType().getStillPath());
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        controller.updateCamera();

        Main.getBatch().begin();
        Main.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth() * 3, Gdx.graphics.getHeight() * 3);
        controller.updateGame();
        Main.getBatch().end();

        updateStageDetails();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    public void updateStageDetails() {
        int currentHP = controller.getPlayer().getHero().getCurrentHP();
        int maxHP = controller.getPlayer().getHero().getMaxHP();

        for (int i = 0; i < heartActors.size(); i++) {
            heartActors.get(i).setVisible(true);
            emptyHeartImages.get(i).setVisible(i >= currentHP && i < maxHP);
        }

        levelLabel.setText("Level: " + controller.getPlayer().getPlayerLevel());
        killsLabel.setText("Kills: " + controller.getPlayer().getCurrentGameKills());
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
        UIViewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


    @Override
    public boolean keyDown(int i) {return false;}

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}

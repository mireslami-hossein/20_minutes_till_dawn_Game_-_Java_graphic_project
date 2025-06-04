package untilDown.com.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import untilDown.com.Controllers.GameController;
import untilDown.com.Main;
import untilDown.com.Models.GameKeysManager;
import untilDown.com.Models.HeartAnimationActor;
import untilDown.com.Models.Player;
import untilDown.com.Models.gun.PlayerGun; // Added import
import untilDown.com.Models.hero.Hero;     // Added import


import java.util.ArrayList;
import java.util.List;

public class GameView implements Screen, InputProcessor {

    private GameController controller;
    private Stage stage;
    private Skin skin;

    private Texture background;

    private final int heartSize = 32;
    private final int maxDisplayableHearts = 10;

    private Texture[] HPHeartTextures;
    private Texture HPHeartEmptiedTexture;
    private List<HeartAnimationActor> heartActors = new ArrayList<>();
    private List<Image> emptyHeartImages = new ArrayList<>();

    private Image heroImage;
    private Label levelLabel;
    private Label killsLabel;
    private ProgressBar xpBar;

    private Image gunImage;
    private Label gunAmmoLabel;

    private Label timeLabel;

    private ScreenViewport gameViewport;
    private ScreenViewport UIViewport;

    private Table topTable;
    private Table bottomTable;


    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        controller.setView(this);

        levelLabel = new Label("", skin);
        killsLabel = new Label("", skin);
        xpBar = new ProgressBar(0, 100, 1, false, skin);
        gunAmmoLabel = new Label("", skin);
        timeLabel = new Label("", skin);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);

        background = new Texture(Gdx.files.internal("background.png"));

        controller.setCamera();

        gameViewport = new ScreenViewport(controller.getCamera());
        UIViewport = new ScreenViewport();
        stage = new Stage(UIViewport, Main.getBatch());


        HPHeartTextures = new Texture[3];
        for (int i = 0; i < HPHeartTextures.length; i++) {
            HPHeartTextures[i] = new Texture(Gdx.files.internal("heart/HeartAnimation_" + i + ".png"));
        }
        HPHeartEmptiedTexture = new Texture(Gdx.files.internal("heart/HeartEmpty.png"));

        topTable = new Table();
        topTable.top().left();
        topTable.setFillParent(true);
        topTable.pad(10);

        Table heartsTable = new Table();
        for (int i = 0; i < maxDisplayableHearts; i++) {
            HeartAnimationActor heart = new HeartAnimationActor(HPHeartTextures, 0, 0, heartSize, heartSize, 0.3f);
            heart.setVisible(false);
            heartActors.add(heart);
            heartsTable.add(heart).size(heartSize).padRight(2);

            Image emptyHeart = new Image(new TextureRegionDrawable(new TextureRegion(HPHeartEmptiedTexture)));
            emptyHeart.setSize(heartSize, heartSize);
            emptyHeart.setVisible(false);
            emptyHeartImages.add(emptyHeart);
        }
        topTable.add(heartsTable).left().padRight(20);

        heroImage = new Image(new Texture(controller.getPlayer().getHeroType().getPortraitPath()));
        topTable.add(heroImage).size(heartSize * 1.5f).padRight(20);

        levelLabel.setFontScale(1.5f);
        topTable.add(levelLabel).padRight(50);

        killsLabel.setFontScale(1.5f);
        topTable.add(killsLabel);
        topTable.row();

        xpBar.setValue(75);
        xpBar.setColor(new Color(0f, 0.7f, 0f, 0.7f));
        topTable.add(xpBar).colspan(4).growX().height(20).padTop(20);


        bottomTable = new Table();
        bottomTable.bottom().left();
        bottomTable.setFillParent(true);
        bottomTable.pad(20);

        gunImage = new Image(new Texture(controller.getPlayer().getGunType().getStillPath()));
        bottomTable.add(gunImage).size(heartSize * 2f).padRight(10);

        gunAmmoLabel.setColor(Color.GRAY);
        gunAmmoLabel.setFontScale(1.5f);
        bottomTable.add(gunAmmoLabel);

        stage.addActor(topTable);
        stage.addActor(bottomTable);
    }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        controller.updateCamera();

        Main.getBatch().setProjectionMatrix(controller.getCamera().combined);
        Main.getBatch().begin();
        Main.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth() * 3, Gdx.graphics.getHeight() * 3);
        controller.updateGame(delta);
        Main.getBatch().end();

        updateStageDetails();
        stage.getViewport().apply();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    public void updateStageDetails() {
        Player player = controller.getPlayer();
        Hero playerHero = player.getHero();
        PlayerGun playerGun = player.getGun();

        int currentHP = playerHero.getCurrentHP();
        int maxHP = playerHero.getMaxHP();


        Table heartsTable = (Table) topTable.getCells().get(0).getActor();
        heartsTable.clearChildren();
        for (int i = 0; i < maxDisplayableHearts; i++) {
            if (i < maxHP) {
                if (i < currentHP) {
                    heartsTable.add(heartActors.get(i)).size(heartSize).padRight(2);
                    heartActors.get(i).setVisible(true);
                    emptyHeartImages.get(i).setVisible(false);
                } else {
                    Image emptyHeart = new Image(HPHeartEmptiedTexture);
                    heartsTable.add(emptyHeart).size(heartSize).padRight(2);
                    heartActors.get(i).setVisible(false);
                }
            }
        }


        levelLabel.setText("Level: " + player.getPlayerLevel());
        killsLabel.setText("Kills: " + player.getCurrentGameKills());
        int maxOfThisLevelXP = player.getMaxXPForNextLevel(player.getPlayerLevel());
        int minOfThisLevelXP = player.getMaxXPForNextLevel(player.getPlayerLevel() - 1);
        levelLabel.setText("XP: " + player.getCurrentGameXP() + "   Level: " + player.getPlayerLevel());
        xpBar.setValue(((float)(player.getCurrentGameXP() - minOfThisLevelXP)/(maxOfThisLevelXP - minOfThisLevelXP)) * 100);

        StringBuilder ammoSlashes = new StringBuilder();
        for (int i = 0; i < playerGun.getCurrentAmmo(); i++) {
            ammoSlashes.append("/");
        }
        if (playerGun.isReloading()) {
            gunAmmoLabel.setText("Reloading...");
        } else {
            gunAmmoLabel.setText("(" + playerGun.getCurrentAmmo() + ") " + ammoSlashes);
        }
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height,false);
        UIViewport.update(width, height, true);
        topTable.invalidateHierarchy();
        bottomTable.invalidateHierarchy();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        if (background != null) background.dispose();
        if (HPHeartEmptiedTexture != null) HPHeartEmptiedTexture.dispose();
        if (HPHeartTextures != null) {
            for (Texture texture : HPHeartTextures) {
                if (texture != null) texture.dispose();
            }
        }
        if (heroImage != null && heroImage.getDrawable() != null) ((TextureRegionDrawable)heroImage.getDrawable()).getRegion().getTexture().dispose();
        if (gunImage != null && gunImage.getDrawable() != null) ((TextureRegionDrawable)gunImage.getDrawable()).getRegion().getTexture().dispose();

        if (stage != null) stage.dispose();
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 worldCoordinates = new Vector3(screenX, screenY, 0);
        controller.getCamera().unproject(worldCoordinates);
        if (button == GameKeysManager.getManager().getKey("shoot")) {
            controller.getWeaponController().handleShoot(worldCoordinates.x, worldCoordinates.y);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector3 worldCoordinates = new Vector3(screenX, screenY, 0);
        controller.getCamera().unproject(worldCoordinates);
        controller.getWeaponController().handleWeaponRotation(worldCoordinates.x, worldCoordinates.y);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

package untilDown.com.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import untilDown.com.Controllers.PreGameMenuController;
import untilDown.com.Main;
import untilDown.com.Models.App;
import untilDown.com.Models.GameAssetManager;
import untilDown.com.Models.Setting;
import untilDown.com.Models.gun.GunType;
import untilDown.com.Models.hero.HeroType;

import javax.swing.event.ChangeEvent;

public class PreGameMenuView implements Screen {
    private final PreGameMenuController controller;

    private Stage stage;

    private Label gameTitleLabel;
    private Label pregameMenuTitle;

    private Label selectHeroLabel;
    private SelectBox<HeroType> selectHero;
    private Texture selectedHeroTuxture;
    private Image selectedHeroImage;

    private Label selectGunLabel;
    private SelectBox<GunType> selectGun;
    private Texture selectedGunTuxture;
    private Image selectedGunImage;

    private Label timeLabel;
    private SelectBox<Integer> selectGameTime;
    private TextButton playButton;
    private Table table;

    public PreGameMenuView(PreGameMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        gameTitleLabel = new Label(App.getApp().getGameTitle(), skin);
        pregameMenuTitle = new Label("PreGame Menu", skin);

        selectHeroLabel = new Label("Hero", skin);
        selectHero = new SelectBox<>(skin);
        selectedHeroTuxture = new Texture(Setting.heroSelected.getPath());
        selectedHeroImage = new Image(selectedHeroTuxture);

        selectGunLabel = new Label("Gun", skin);
        selectGun = new SelectBox<>(skin);
        selectedGunTuxture = new Texture(Setting.gunSelcected.getStillPath());
        selectedGunImage = new Image(selectedGunTuxture);

        timeLabel = new Label("Time (minutes)", skin);
        selectGameTime = new SelectBox<>(skin);

        playButton = controller.getPlayButton();
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(1200, 1280));
        table = new Table(GameAssetManager.getManager().getSkin());

        Gdx.input.setInputProcessor(stage);
        table.setFillParent(true);
        table.center();

        gameTitleLabel.setFontScale(4f);
        gameTitleLabel.setColor(Color.RED);
        table.add(gameTitleLabel).expandX();

        table.row().padTop(75);
        pregameMenuTitle.setFontScale(2f);
        table.add(pregameMenuTitle).center();

        table.row().padTop(75);
        Table mainTable = new Table(GameAssetManager.getManager().getSkin());

        selectHeroLabel.setFontScale(1.5f);
        mainTable.add(selectHeroLabel);
        mainTable.add(selectHero).width(App.fieldWidth).padLeft(50);
        selectHero.setItems(HeroType.values());
        selectHero.setSelected(Setting.heroSelected);

        mainTable.row().padTop(20);
        mainTable.add(selectedHeroImage).size(100,100).center();

        mainTable.row().padTop(50);
        selectGunLabel.setFontScale(1.5f);
        mainTable.add(selectGunLabel);
        mainTable.add(selectGun).width(App.fieldWidth).padLeft(50);
        selectGun.setItems(GunType.values());
        selectGun.setSelected(Setting.gunSelcected);

        mainTable.row().padTop(20);
        mainTable.add(selectedGunImage).size(100,100).center();

        mainTable.row().padTop(50);
        timeLabel.setFontScale(1.5f);
        mainTable.add(timeLabel);
        mainTable.add(selectGameTime).width(App.fieldWidth).padLeft(50);
        selectGameTime.setItems(2,5,10,20);
        table.add(mainTable).expandX();

        table.row().padTop(75);
        table.add(playButton).width(App.fieldWidth/2).center();

        setSelectListeners();
        stage.addActor(table);
    }

    public void setSelectListeners() {
        selectHero.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                selectedHeroTuxture = new Texture(Setting.heroSelected.getPath());
                selectedHeroImage.setDrawable(new TextureRegionDrawable(new TextureRegion(selectedHeroTuxture)));
            }
        });

        selectGun.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                selectedGunTuxture = new Texture(Setting.gunSelcected.getStillPath());
                selectedGunImage.setDrawable(new TextureRegionDrawable(new TextureRegion(selectedGunTuxture)));
            }
        });
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

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
        if (stage != null) {
            stage.dispose();
        }
    }

    public TextButton getPlayButton() {
        return playButton;
    }
}

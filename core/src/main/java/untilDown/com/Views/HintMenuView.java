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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import untilDown.com.Controllers.PreGameMenuController;
import untilDown.com.Models.*;
import untilDown.com.Models.hero.HeroType;

import java.util.HashMap;

public class HintMenuView implements Screen {
    private final PreGameMenuController controller;

    private Stage stage;

    private Label gameTitleLabel;
    private Label hintMenuTitle;

    private Label selectHeroLabel;
    private SelectBox<HeroType> selectHero;
    private Label heroHintLabel;

    private Texture selectedHeroTuxture;
    private Image selectedHeroImage;

    private Label selectAbilityLabel;

    private SelectBox<AbilityType> selectAbility;
    private Label abilityHintLabel;


    private TextButton keyControlsButton;
    private TextButton backButton;
    private Table table;
    private Window controlsWindow;

    public HintMenuView(PreGameMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        gameTitleLabel = new Label(App.getApp().getGameTitle(), skin);
        hintMenuTitle = new Label("PreGame Menu", skin);

        selectHeroLabel = new Label("Hero", skin);
        heroHintLabel = new Label("", skin);
        selectHero = controller.getHeroTypeSelectBox();
        selectedHeroTuxture = new Texture(Setting.heroSelected.getPortraitPath());
        selectedHeroImage = new Image(selectedHeroTuxture);

        selectAbilityLabel = new Label("Ability", skin);
        abilityHintLabel = new Label("", skin);
        selectAbility = controller.getAbilityTypeSelectBox();

        keyControlsButton = new TextButton("Key Controls", skin);
        controlsWindow = new Window("Controls", skin);
        backButton = controller.getBackButton();
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
        hintMenuTitle.setFontScale(2f);
        table.add(hintMenuTitle).center();

        table.row().padTop(75);
        Table mainTable = new Table(GameAssetManager.getManager().getSkin());

        selectHeroLabel.setFontScale(1.5f);
        mainTable.add(selectHeroLabel);
        mainTable.add(selectHero).width(App.fieldWidth).padLeft(50);

        mainTable.row().padTop(35);
        mainTable.add(selectedHeroImage).size(100,100).center();
        mainTable.add(heroHintLabel).center().padLeft(25);

        mainTable.row().padTop(50);
        selectAbilityLabel.setFontScale(1.5f);
        mainTable.add(selectAbilityLabel);
        mainTable.add(selectAbility).width(App.fieldWidth).padLeft(75);

        mainTable.row().padTop(35);
        mainTable.add();
        mainTable.add(abilityHintLabel).center().pad(50);

        mainTable.row().padTop(50).colspan(2);
        mainTable.add(keyControlsButton).width(App.fieldWidth).center();
        table.add(mainTable).expandX();

        table.row().padTop(50);
        table.add(backButton).width(App.fieldWidth/2).center();

        setSelectListeners();

        stage.addActor(table);
    }

    public void setSelectListeners() {
        selectHero.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Setting.heroSelected = selectHero.getSelected();
                selectedHeroTuxture = new Texture(Setting.heroSelected.getPortraitPath());
                selectedHeroImage.setDrawable(new TextureRegionDrawable(new TextureRegion(selectedHeroTuxture)));
            }
        });

        setControlsWindow();
        keyControlsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                showControlsWindow();
            }
        });
    }

    public void setControlsWindow() {
        controlsWindow.setSize(750, 1000);

        controlsWindow.setPosition(
            Gdx.graphics.getWidth() / 2f - controlsWindow.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - controlsWindow.getHeight() / 2f
        );

        Table contentTable = new Table();
        contentTable.pad(20);
        contentTable.defaults().pad(10).left();

        contentTable.row();
        GameKeysManager gameKeysManager = GameKeysManager.getManager();
        HashMap<String, Integer> gameKeys = gameKeysManager.getGameKeys();
        for (String key : gameKeys.keySet()) {
            contentTable.row().padTop(20);
            Label keyWord = new Label(key, GameAssetManager.getManager().getSkin());
            String keyName = GameKeysManager.getManager().getKeyName(gameKeys.get(key));
            TextButton button = new TextButton(keyName, GameAssetManager.getManager().getSkin());

            contentTable.add(keyWord);
            contentTable.add(button).padRight(15);
        }

        contentTable.row().padTop(35);
        TextButton back = new TextButton("Back", GameAssetManager.getManager().getSkin());
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controlsWindow.remove();
            }
        });
        contentTable.add(back).colspan(2).center().width(600);

        ScrollPane scrollPane = new ScrollPane(contentTable, GameAssetManager.getManager().getSkin());
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);

        controlsWindow.add(scrollPane).expand().fill().pad(10).row();

        //TODO : add cheat codes
    }

    public void showControlsWindow() {
        stage.addActor(controlsWindow);
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

    public Label getHeroHintLabel() {
        return hintMenuTitle;
    }

    public void setHeroHintLabel(String heroHintLabel) {
        this.heroHintLabel.setText(heroHintLabel);
    }

    public Label getAbilityHintLabel() {
        return abilityHintLabel;
    }

    public void setAbilityHintLabel(String abilityHintLabel) {
        this.abilityHintLabel.setText(abilityHintLabel);
    }
}

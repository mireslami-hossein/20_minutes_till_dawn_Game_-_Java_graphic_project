package untilDown.com.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import untilDown.com.Controllers.PreGameMenuController;
import untilDown.com.Main;
import untilDown.com.Models.App;

public class PreGameMenuView implements Screen {
    private final PreGameMenuController controller;

    private Stage stage;

    private Label gameTitleLabel;
    private Label pregameMenuTitle;

    private Label selectHeroLabel;
    private final SelectBox selectHero;
    private Texture selectedHero;
    private Image selectedHeroImage;

    private Label selectGunLabel;
    private final SelectBox selectGun;
    private Texture selectedGun;
    private Image selectedGunImage;

    private final SelectBox time;
    private final TextButton playButton;
    private Table table;

    public PreGameMenuView(PreGameMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        gameTitleLabel = new Label(App.getApp().getGameTitle(), skin);
        pregameMenuTitle = new Label("PreGame Menu", skin);

        selectHeroLabel = new Label("Hero", skin);
        selectHero = new SelectBox<>(skin);
        selectedHero = new Texture("Hero.png");


        playButton = new TextButton("Play", skin);
        table = new Table(skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        table = new Table();
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        // Array for select Hero dropdown
        Array<String> heros = new Array<>();
        heros.add("Hero 1");
        heros.add("Hero 2");
        heros.add("Hero 3");

        selectHero.setItems(heros);

        table.setFillParent(true);
        table.center();
        table.add(pregameMenuTitle);
        table.row().pad(10, 0, 10, 0);
        table.add(selectHero);
        table.row().pad(10, 0, 10, 0);
        table.add(playButton);

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);

        controller.handleStartGame();
        Main.getBatch().begin();
        Main.getBatch().end();

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

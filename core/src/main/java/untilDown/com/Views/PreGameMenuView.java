package untilDown.com.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import untilDown.com.Controllers.PreGameMenuController;
import untilDown.com.Main;

import java.util.ArrayList;

public class PreGameMenuView implements Screen {
    private final PreGameMenuController controller;

    private Stage stage;
    private Label gameTitle;

    private final TextButton playButton;
    private final SelectBox selectHero;

    private Table table;

    public PreGameMenuView(PreGameMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        this.gameTitle = new Label("PreGame Menu", skin);
        this.playButton = new TextButton("Play", skin);
        this.selectHero = new SelectBox<>(skin);

        this.table = new Table(skin);
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
        table.add(gameTitle);
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

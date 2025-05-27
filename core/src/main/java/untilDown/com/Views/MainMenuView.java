package untilDown.com.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import untilDown.com.Controllers.MainMenuController;
import untilDown.com.Main;

public class MainMenuView implements Screen {
    private Stage stage;
    private TextButton playButton;
    private Label gameTitle;
    private TextField textField;

    public Table table;
    private final MainMenuController controller;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        this.gameTitle = new Label("20 Minutes Until Dawn", skin);
        this.playButton = new TextButton("Play", skin);
        this.textField = new TextField("enter your username", skin);

        this.table = new Table();
    }


    @Override
    public void show() {
        // This 2 line should be in all menus
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage); // add input processor

        table.setFillParent(true); // to center the table in the page
        table.center();
        table.add(gameTitle);
        table.row().pad(10, 0, 10, 0);
        table.add(textField).width(600);
        table.row().pad(10, 0, 10, 0);
        table.add(playButton);

        stage.addActor(table); // To add table to stage of view
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();

//        controller.handleMainMenuButton();
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

    }


    public TextButton getPlayButton() {
        return playButton;
    }

    public TextField getTextField() {
        return textField;
    }
}

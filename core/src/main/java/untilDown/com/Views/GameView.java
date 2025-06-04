package untilDown.com.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import untilDown.com.Controllers.GameController;
import untilDown.com.Main;

// by setting InputProcessor to GameView we can handle each Key doing easily!
public class GameView implements Screen, InputProcessor {

    private GameController controller;
    private Stage stage;

    private Texture background;

    private ScreenViewport viewport;


    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        viewport = new ScreenViewport();
    }


    @Override
    public void show() {
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(this);

        background = new Texture(Gdx.files.internal("background.png"));
        controller.setCamera();
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);

        controller.updateCamera();

        Main.getBatch().setProjectionMatrix(controller.getCamera().combined);
        Main.getBatch().begin();
        Main.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth() * 2, Gdx.graphics.getHeight() * 2);
        controller.updateGame();
        Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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

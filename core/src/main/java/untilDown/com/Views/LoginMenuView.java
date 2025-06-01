package untilDown.com.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import untilDown.com.Controllers.LoginMenuController;
import untilDown.com.Main;
import untilDown.com.Models.App;

public class LoginMenuView implements Screen, InputProcessor {
    private LoginMenuController controller;
    private Stage stage;

    private Label gameTitleLabel;
    private Label loginMenuTitle;
    private TextField username;

    private TextField password;
    private TextButton showPassword;

    private Label alert;

    private TextButton loginButton;
    private TextButton forgotPasswordButton;
    private TextButton backToSignupMenuButton;

    private Table table;

    public LoginMenuView(LoginMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        gameTitleLabel = new Label(App.getApp().getGameTitle(), skin);
        loginMenuTitle = new Label("Login Menu", skin);

        username = new TextField("", skin);
        password = new TextField("", skin);

        showPassword = new TextButton("Show", skin);


        loginButton = new TextButton("Enter", skin);
        forgotPasswordButton= new TextButton("Forget Password", skin);
        backToSignupMenuButton = new TextButton("Back To Register", skin);

        alert = new Label("", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(1200, 1280));
        table = new Table();
        stage.clear(); // To load the stage correct after returning to this screen
        Gdx.input.setInputProcessor(stage);

        Table rootTable = new Table();

        rootTable.setFillParent(true); // to center the table in the page
        rootTable.center();

        table.center();

        gameTitleLabel.setFontScale(4f);
        gameTitleLabel.setColor(Color.RED);
        rootTable.add(gameTitleLabel).expandX();

        table.row().padTop(75);
        loginMenuTitle.setFontScale(2f);
        table.add(loginMenuTitle).center();

        rootTable.row().padTop(75);
        table.row().padTop(50);
        username.setMessageText("Username");
        table.add(username).width(App.fieldWidth).padBottom(20);

        table.row();
        password.setMessageText("Password");
        password.setPasswordMode(true);
        password.setPasswordCharacter('*');
        table.add(password).width(App.fieldWidth).padBottom(20);
        table.add(showPassword).width(200).padLeft(10);

        // listeners for show password
        Main.getMain().getSignupMenuView().addShowPassListener(showPassword, password);

        table.row().padTop(70);
        table.add(loginButton).width(App.fieldWidth);

        table.row().padTop(20);
        table.add(forgotPasswordButton).width(App.fieldWidth);

        table.row().padTop(20);
        table.add(backToSignupMenuButton).width(App.fieldWidth);

        table.row().padTop(50);
        alert.setColor(Color.RED);

        table.add(alert);

        loginButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                alert.setText(controller.handleLogin(username.getText(), password.getText()));
            }
        });

        forgotPasswordButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().navigateToForgetPass();
            }
        });

        backToSignupMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().navigateToSignupMenu();
            }
        });
        rootTable.add(table);
        stage.addActor(rootTable);
    }

    public void addShowPassListener(TextButton button, TextField field) {
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                boolean isPassMode = field.isPasswordMode();
                field.setPasswordMode(!isPassMode);
                button.setText(field.isPasswordMode() ? "Show" : "Hide");
            }
        });
    }

    public LoginMenuController getController() {
        return controller;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Batch batch = Main.getBatch();
        batch.begin();
        batch.end();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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



    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            Main.getMain().navigateToSignupMenu();
            return true;
        }
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
        return false;
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
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

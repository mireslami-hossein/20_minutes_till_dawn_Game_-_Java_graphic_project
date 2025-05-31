package untilDown.com.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import untilDown.com.Controllers.LoginMenuController;
import untilDown.com.Main;
import untilDown.com.Models.App;

public class ForgetPassView implements Screen {
    private LoginMenuController controller;
    private Stage stage;

    private Label gameTitleLabel;
    private Label forgetPassLabel;
    private TextField username;

    private Label securityQuestionLabel;
    private TextField answer;

    private TextField newPassword;
    private TextButton showPassword;

    private Label alert;

    private TextButton changePassword;
    private TextButton backToLogin;

    private Table table;

    public ForgetPassView(LoginMenuController controller, Skin skin) {
        this.controller = controller;

        gameTitleLabel = new Label(App.getApp().getGameTitle(), skin);
        forgetPassLabel = new Label("Forgot Password", skin);

        username = new TextField("", skin);
        newPassword = new TextField("", skin);

        securityQuestionLabel = new Label("What was your first school name?", skin);
        answer = new TextField("", skin);

        showPassword = new TextButton("Show", skin);

        changePassword = new TextButton("Change Password", skin);
        backToLogin = new TextButton("Back To Login", skin);

        alert = new Label("", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        table = new Table();
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        Table rootTable = new Table();

        rootTable.setFillParent(true); // to center the table in the page
        rootTable.center();

        table.center();

        gameTitleLabel.setFontScale(4f);
        gameTitleLabel.setColor(Color.RED);
        rootTable.add(gameTitleLabel).expandX();

        table.row().padTop(75);
        forgetPassLabel.setFontScale(2f);
        table.add(forgetPassLabel).center();

        rootTable.row().padTop(75);
        table.row().padTop(50);
        username.setMessageText("Username");
        table.add(username).width(App.fieldWidth);

        table.row().padTop(20);
        newPassword.setMessageText("new password");
        newPassword.setPasswordMode(true);
        newPassword.setPasswordCharacter('*');
        table.add(newPassword).width(App.fieldWidth);
        table.add(showPassword).width(200).padLeft(10);

        table.row().padTop(20);
        table.add(securityQuestionLabel).width(App.fieldWidth);
        table.row().padTop(20);
        table.add(answer).width(App.fieldWidth);

        // listeners for show password
        addShowPassListener(showPassword, newPassword);

        table.row().padTop(70);
        table.add(changePassword).width(App.fieldWidth);

        table.row().padTop(20);
        table.add(backToLogin).width(App.fieldWidth);

        table.row().padTop(50);
        alert.setColor(Color.RED);

        table.add(alert);

        changePassword.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                alert.setText(controller.handleSetNewPass(username.getText(), newPassword.getText(), answer.getText()));
            }
        });

        backToLogin.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().navigateToLoginMenu();
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
    public void dispose() {}

}

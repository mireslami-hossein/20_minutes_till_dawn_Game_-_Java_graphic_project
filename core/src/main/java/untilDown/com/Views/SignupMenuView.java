package untilDown.com.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import untilDown.com.Controllers.SignupMenuController;
import untilDown.com.Main;
import untilDown.com.Models.App;
import untilDown.com.Models.GameAssetManager;
import untilDown.com.Models.User;

import java.awt.event.TextListener;


public class SignupMenuView implements Screen {
    private SignupMenuController controller;
    private Stage stage;

    private Label gameTitleLabel;
    private Label signupMenuTitle;
    private TextField username;

    private TextField password;
    private TextButton showPassword;
    private TextField confirmPassword;
    private TextButton showConfirmPassword;

    private Label securityQuestionLabel;
    private TextField answerField;

    private Label alert;

    private TextButton signupButton;
    private TextButton loginAsGuestButton;

    private Table table;

    public SignupMenuView(SignupMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        table = new Table();
        gameTitleLabel = new Label(App.getApp().getGameTitle(), skin);
        signupMenuTitle = new Label("Signup", skin);

        username = new TextField("", skin);
        password = new TextField("", skin);

        showPassword = new TextButton("Show", skin);
        showConfirmPassword = new TextButton("Show", skin);

        confirmPassword = new TextField("", skin);

        securityQuestionLabel = new Label("What was your first school name?", skin);
        answerField = new TextField("", skin);

        signupButton = new TextButton("Sign Up", skin);
        loginAsGuestButton = new TextButton("Login As Guest", skin);

        alert = new Label("", skin);
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Table rootTable = new Table();

        rootTable.setFillParent(true); // to center the table in the page
        rootTable.center();

        table.center();

        gameTitleLabel.setFontScale(4f);
        gameTitleLabel.setColor(Color.RED);
        rootTable.add(gameTitleLabel).expandX();

        table.row().padTop(75);
        signupMenuTitle.setFontScale(2f);
        table.add(signupMenuTitle).center();

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

        table.row();
        confirmPassword.setMessageText("Confirm Password");
        confirmPassword.setPasswordMode(true);
        confirmPassword.setPasswordCharacter('*');
        table.add(confirmPassword).width(App.fieldWidth).padBottom(30);
        table.add(showConfirmPassword).width(200).padLeft(10);

        // listeners for show password
        addShowPassListener(showPassword, password);
        addShowPassListener(showConfirmPassword, confirmPassword);

        table.row();
        table.add(securityQuestionLabel);

        table.row();
        answerField.setMessageText("Answer of Security question");
        table.add(answerField).width(App.fieldWidth);

        table.row().padTop(70);
        table.add(signupButton).width(App.fieldWidth);

        table.row().padTop(20);
        table.add(loginAsGuestButton).width(App.fieldWidth);

        table.row().padTop(50);
        alert.setColor(Color.RED);

        table.add(alert);

        signupButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 alert.setText(controller.handleSignup(username.getText(), password.getText(), confirmPassword.getText(), answerField.getText()));
            }
        });

        loginAsGuestButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int id = (int) (Math.random()*100);
                App.getApp().setLoggedInUser(new User("Guest-"+id, "", "", false));
                Main.getMain().navigateToMainMenu();
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
    public void dispose() {

    }
}

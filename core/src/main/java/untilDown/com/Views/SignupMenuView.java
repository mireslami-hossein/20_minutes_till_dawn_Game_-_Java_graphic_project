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


public class SignupMenuView implements Screen {
    private SignupMenuController controller;
    private Stage stage;

    private Label gameTitleLabel;
    private Label signupMenuTitle;
    private TextField username;
    private TextField password;
    private TextField confirmPassword;
    private TextButton signupButton;

    private Label securityQuestionLabel;
    private TextField answerField;

    private Label alert;

    private Table table;

    public SignupMenuView(SignupMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        table = new Table();
        gameTitleLabel = new Label(App.getApp().getGameTitle(), skin);
        signupMenuTitle = new Label("Signup", skin);

        username = new TextField("", skin);
        password = new TextField("", skin);
        confirmPassword = new TextField("", skin);
        signupButton = new TextButton("Sign Up", skin);

        securityQuestionLabel = new Label("What was your first school name?", skin);
        answerField = new TextField("", skin);

        alert = new Label("", skin);
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true); // to center the table in the page
        table.center();

        gameTitleLabel.setFontScale(4f);
        gameTitleLabel.setColor(Color.RED);
        table.add(gameTitleLabel).expandX().center();

        table.row().padTop(75);
        signupMenuTitle.setFontScale(2f);
        table.add(signupMenuTitle).expandX().center();

        table.row().padTop(50);
        username.setMessageText("Username");
        table.add(username).width(600).padBottom(20);

        table.row();
        password.setMessageText("Password");
        password.setPasswordMode(true);
        password.setPasswordCharacter('*');
        table.add(password).width(600).padBottom(20);

        table.row();
        confirmPassword.setMessageText("Confirm Password");
        confirmPassword.setPasswordMode(true);
        confirmPassword.setPasswordCharacter('*');
        table.add(confirmPassword).width(600).padBottom(30);

        table.row();
        table.add(securityQuestionLabel);

        table.row();
        answerField.setMessageText("Answer of Security question");
        table.add(answerField).width(600).padBottom(50);

        table.row();
        table.add(signupButton).width(600);

        table.row().padTop(50);
        alert.setColor(Color.RED);

        table.add(alert);

        signupButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 alert.setText(controller.handleSignup(username.getText(), password.getText(), confirmPassword.getText()));
            }
        });

        stage.addActor(table);
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

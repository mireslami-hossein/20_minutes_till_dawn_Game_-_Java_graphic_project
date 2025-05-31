package untilDown.com.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import untilDown.com.Controllers.MainMenuController;
import untilDown.com.Main;
import untilDown.com.Models.App;
import untilDown.com.Models.User;

public class MainMenuView implements Screen {
    private Stage stage;

    private Label gameTitleLabel;

    private Texture avatarTexture;
    private Image avatarImage;
    private Label userNameLabel;
    private Label userScores;

    private TextButton settingsButton;
    private TextButton profileButton;
    private TextButton pregameMenuButton;
    private TextButton scoreboardButton;
    private TextButton hintMenuButton;
    private TextButton savedGameButton;
    private TextButton logoutButton;


    public Table table;
    private final MainMenuController controller;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        table = new Table();
        gameTitleLabel = new Label(App.getApp().getGameTitle(), skin);

        User loggedInUser = App.getApp().getLoggedInUser();

        String avatarPath = loggedInUser.getAvatarAddress();
        avatarTexture = new Texture(avatarPath);
        avatarImage = new Image(avatarTexture);
        userNameLabel = new Label(loggedInUser.getUsername(), skin);
        userScores = new Label("Scores :  " + loggedInUser.getScore(), skin);


        this.settingsButton = new TextButton("Setting", skin);
        this.profileButton = new TextButton("Profile", skin);
        this.pregameMenuButton = new TextButton("Pregame Menu", skin);
        this.scoreboardButton = new TextButton("Scoreboard", skin);
        this.hintMenuButton = new TextButton("Hint Menu", skin);
        this.savedGameButton = new TextButton("Load Saved Game", skin);

        this.logoutButton = new TextButton("Logout", skin);
    }


    @Override
    public void show() {
        stage = new Stage();
        table = new Table().debugTable();

        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true); // to center the table in the page
        table.top();


        Table userDetailsTable = new Table();
        userDetailsTable.debugTable();
        userDetailsTable.add(avatarImage).size(100, 100).padRight(20);

        userDetailsTable.padLeft(100);
        userNameLabel.setFontScale(2f);
        userDetailsTable.add(userNameLabel).left();
        userScores.setFontScale(2f);
        userScores.setColor(Color.CYAN);
        userDetailsTable.add(userScores).left().padLeft(600);

        table.add(userDetailsTable).expandX().fillX();
        table.row().padTop(75);
        gameTitleLabel.setFontScale(4f);
        gameTitleLabel.setColor(Color.RED);
        table.add(gameTitleLabel).expandX();

        table.row().padTop(75);
        table.add(settingsButton).width(App.fieldWidth);

        table.row().padTop(30);
        table.add(profileButton).width(App.fieldWidth);

        table.row().padTop(30);
        table.add(pregameMenuButton).width(App.fieldWidth);

        table.row().padTop(30);
        table.add(scoreboardButton).width(App.fieldWidth);

        table.row().padTop(30);
        table.add(hintMenuButton).width(App.fieldWidth);

        table.row().padTop(30);
        table.add(savedGameButton).width(App.fieldWidth);

        table.row().padTop(50);
        table.add(logoutButton).width(450);

        stage.addActor(table); // To add table to stage of view

        setButtonsListener();
    }

    private void setButtonsListener() {
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        profileButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        pregameMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        scoreboardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        hintMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        savedGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        logoutButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                App.getApp().setLoggedInUser(null);
                Main.getMain().navigateToLoginMenu();
            }
        });

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
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
}

package untilDown.com.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import untilDown.com.Controllers.MainMenuController;
import untilDown.com.Main;
import untilDown.com.Models.App;
import untilDown.com.Models.GameAssetManager;
import untilDown.com.Models.GameAssetManager.MusicOfGame;
import untilDown.com.Models.GameKeysManager;
import untilDown.com.Models.User;

import java.util.HashMap;

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


    // windows
    private Window settingsWindow;
    private  Window controlsWindow;
    private boolean waitingForKey = false;

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

        // window
        settingsWindow = new Window("Settings", skin);
        controlsWindow = new Window("Key Controls", skin);
    }


    @Override
    public void show() {
        stage = new Stage(new FitViewport(1200, 1280));
        table = new Table();

        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true); // to center the table in the page
        table.top();


        Table userDetailsTable = new Table();
        userDetailsTable.add(avatarImage).size(100, 100).padRight(20);

//        userDetailsTable.padLeft(100);
        userNameLabel.setFontScale(2f);
        userDetailsTable.add(userNameLabel).left();
        userScores.setFontScale(2f);
        userScores.setColor(Color.CYAN);
        userDetailsTable.add(userScores).left().padLeft(App.fieldWidth);

        table.add(userDetailsTable).expandX().fillX();
        table.row().padTop(50);
        gameTitleLabel.setFontScale(4f);
        gameTitleLabel.setColor(Color.RED);
        table.add(gameTitleLabel).expandX();

        table.row().padTop(50);
        table.add(settingsButton).width(App.fieldWidth);

        table.row().padTop(15);
        table.add(profileButton).width(App.fieldWidth);

        table.row().padTop(15);
        table.add(pregameMenuButton).width(App.fieldWidth);

        table.row().padTop(15);
        table.add(scoreboardButton).width(App.fieldWidth);

        table.row().padTop(15);
        table.add(hintMenuButton).width(App.fieldWidth);

        table.row().padTop(15);
        table.add(savedGameButton).width(App.fieldWidth);

        table.row().padTop(30);
        table.add(logoutButton).width(450);

        stage.addActor(table); // To add table to stage of view

        setButtonsListener();

        setSettingWindow();
        setControlsWindow();
    }

    //TODO : clean code
    private void setButtonsListener() {
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addActor(settingsWindow);
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
                controller.logoutUser();
            }
        });

    }

    private void setSettingWindow() {
        Skin skin = GameAssetManager.getManager().getSkin();
        GameAssetManager assetManager = GameAssetManager.getManager();

        settingsWindow.setSize(1000, 1000);
        settingsWindow.setPosition(
            Gdx.graphics.getWidth() / 2f - settingsWindow.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - settingsWindow.getHeight() / 2f
        );

        settingsWindow.pad(20);
        settingsWindow.defaults().pad(10).left();

        settingsWindow.row();
        Label musicVolumeLabel = new Label("Music Volume ", skin);
        musicVolumeLabel.setFontScale(1.5f);
        settingsWindow.add(musicVolumeLabel);

        settingsWindow.row().padTop(15);
        Slider musicVolume = new Slider(0, 100, 1, false, skin);
        musicVolume.setValue(assetManager.getMusicVolume());
        settingsWindow.add(musicVolume).width(600);

        settingsWindow.row().padTop(30);
        Label changeMusicLabel = new Label("Background Music ", skin);
        changeMusicLabel.setFontScale(1.5f);
        settingsWindow.add(changeMusicLabel);

        settingsWindow.row().padTop(15);
        SelectBox<String> musicsSelectBox = new SelectBox<>(skin);
        musicsSelectBox.setItems("Music 1", "Music 2", "Music 3");

        MusicOfGame currentMusic = assetManager.getMusic();
        musicsSelectBox.setSelectedIndex(currentMusic.ordinal());
        settingsWindow.add(musicsSelectBox).width(600);

        settingsWindow.row().padTop(30);
        CheckBox sfxEnable = new CheckBox("Game SFX", skin);
        sfxEnable.setChecked(assetManager.isSfxEnabled());
        settingsWindow.add(sfxEnable).width(600);

        settingsWindow.row().padTop(30);
        TextButton keyControlsButton = new TextButton("Key Controls", skin);
        keyControlsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                showControlsWindow();
            }
        });
        settingsWindow.add(keyControlsButton).center();

        settingsWindow.row().padTop(30);
        CheckBox autoReload = new CheckBox("Auto-Reload", skin);
        autoReload.setChecked(App.getApp().isAutoReloadEnabled());
        settingsWindow.add(autoReload).width(600);

        settingsWindow.row().padTop(30);
        CheckBox whiteBlack = new CheckBox("White-Black", skin);
        whiteBlack.setChecked(assetManager.isWhiteBlackEnabled());
        settingsWindow.add(whiteBlack).width(600);


        settingsWindow.row().padTop(50);
        TextButton closeBtn = new TextButton("Back", skin);
        settingsWindow.add(closeBtn).center();
        closeBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                assetManager.setMusicVolume(musicVolume.getValue());
                assetManager.setMusic(musicsSelectBox.getSelectedIndex());
                // complete
                settingsWindow.remove();
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
            addChangeKeyListener(button, gameKeys.get(key));

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
    }

    public void showControlsWindow() {
        stage.addActor(controlsWindow);
    }

    public void addChangeKeyListener(TextButton button, int firstKey) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (waitingForKey) return;

                waitingForKey = true;
                button.setText("Press a key...");
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keyCode) {
                        GameKeysManager.getManager().setKey(firstKey, keyCode);
                        button.setText(GameKeysManager.getManager().getKeyName(keyCode));
                        Gdx.input.setInputProcessor(stage);
                        waitingForKey = false;
                        return true;
                    }

                    @Override
                    public boolean touchDown(int screenX, int screenY, int pointer, int buttonCode) {
                        // Mouse buttons: 0 = left, 1 = right, 2 = middle
                        int mappedCode = -100 - buttonCode;  // left = -100
                        GameKeysManager.getManager().setKey(firstKey, mappedCode);
                        button.setText(GameKeysManager.getManager().getKeyName(mappedCode));
                        Gdx.input.setInputProcessor(stage);
                        waitingForKey = false;
                        return true;
                    }
                });
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

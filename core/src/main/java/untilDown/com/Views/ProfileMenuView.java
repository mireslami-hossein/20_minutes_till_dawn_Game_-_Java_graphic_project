package untilDown.com.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import untilDown.com.Controllers.MainMenuController;
import untilDown.com.Main;
import untilDown.com.Models.App;
import untilDown.com.Models.GameAssetManager;
import untilDown.com.Models.User;
import untilDown.com.Models.avatar.Avatar;
import untilDown.com.Models.avatar.AvatarImage;

public class ProfileMenuView implements Screen {


    private Stage stage;

    private Label gameTitleLabel;

    private Texture avatarTexture;
    private Image avatarImage;
    private TextButton changeAvatarButton;
    private SelectBox<String> avatarSelectBox;
    private Label userScores;

    private Label userNameLabel;
    private TextField changeUserNameField;

    private Label passwordLabel;
    private TextField changePasswordField;
    private TextButton showPass;

    private TextButton saveDataButton;
    private TextButton deleteAccountButton;
    private Label alert;


    public Table table;

    private final MainMenuController controller;


    public ProfileMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setProfileView(this);

        gameTitleLabel = new Label(App.getApp().getGameTitle(), skin);

        User loggedInUser = App.getApp().getLoggedInUser();
        avatarTexture = new Texture(loggedInUser.getAvatarAddress());
        avatarImage = new Image(avatarTexture);
        avatarSelectBox = new SelectBox<>(skin);

        changeAvatarButton = new TextButton("Upload", skin);

        userScores = new Label("Scores :  " + loggedInUser.getScore(), skin);

        userNameLabel = new Label("Username: ", skin);
        changeUserNameField = new TextField(loggedInUser.getUsername(), skin);

        passwordLabel = new Label("Password: ", skin);
        changePasswordField = new TextField(loggedInUser.getPassword(), skin);
        changePasswordField.setPasswordMode(true);
        showPass = new TextButton("Show", skin);

        saveDataButton = new TextButton("Save", skin);
        deleteAccountButton = new TextButton("Delete Account", skin);

        alert = new Label("", skin);
    }


    @Override
    public void show() {
        stage = new Stage(new FitViewport(1200, 1280));
        table = new Table();

        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true); // to center the table in the page
        table.center();

        gameTitleLabel.setFontScale(4f);
        gameTitleLabel.setColor(Color.RED);
        table.add(gameTitleLabel).expandX();

        table.row().padTop(30);

        table.row().padTop(50);
        Table userDetailsTable = new Table();
        userDetailsTable.add(avatarImage).size(100, 100).padRight(20);
        setAvatarSelectBox();
        userDetailsTable.add(avatarSelectBox).width(App.fieldWidth * 0.75f).padRight(20);
        userDetailsTable.add(changeAvatarButton).width((float)App.fieldWidth/2);

        userScores.setFontScale(2f);
        userScores.setColor(Color.CYAN);
        userDetailsTable.add(userScores).left().padLeft(250);

        table.add(userDetailsTable).expandX().fillX();

        table.row();
        Table formTable = new Table();
        formTable.row().padTop(30);
        formTable.add(userNameLabel);
        formTable.row().padTop(15);
        formTable.add(changeUserNameField).width((float)App.fieldWidth);

        formTable.row().padTop(20);
        formTable.add(passwordLabel);
        formTable.row().padTop(15);

        formTable.add(changePasswordField).width((float)App.fieldWidth);
        formTable.add(showPass).width((float)App.fieldWidth/2).left().padLeft(20);
        Main.getMain().getSignupMenuView().addShowPassListener(showPass, changePasswordField);

        handleForGuest();

        formTable.row().padTop(30);
        formTable.add(saveDataButton).width((float)App.fieldWidth);
        formTable.row().padTop(20);
        deleteAccountButton.setColor(0.7f, 0.5f, 0.4f, 1f);
        formTable.add(deleteAccountButton).width((float)App.fieldWidth);
        table.add(formTable);

        table.row().padTop(30);
        alert.setFontScale(1f);
        alert.setColor(Color.RED);
        table.add(alert);
        addButtonsListener();

        stage.addActor(table);
    }

    private void setAvatarSelectBox() {
        Array<String> allAvatars = Avatar.getAllAvatarNames();
        avatarSelectBox.setItems(allAvatars);

        User user = App.getApp().getLoggedInUser();
        AvatarImage userAvatar = user.getAvatar();
        if (userAvatar instanceof Avatar) {
            avatarSelectBox.setSelected(((Avatar) userAvatar).name());
        }

        avatarSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String selected = avatarSelectBox.getSelected();
                selectUserAvatar(selected);
                avatarTexture.dispose();

                avatarTexture = new Texture(user.getAvatarAddress());
                avatarImage.setDrawable(new TextureRegionDrawable(new TextureRegion(avatarTexture)));
            }
        });
    }

    private void handleForGuest() {
        User loggedInUser = App.getApp().getLoggedInUser();

        if (loggedInUser.isGuest()) {
            changeUserNameField.setDisabled(true);
            changeUserNameField.setColor(Color.RED);
            changePasswordField.setDisabled(true);
            changePasswordField.setColor(Color.RED);
        }
    }

    private void addButtonsListener() {
        changeAvatarButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleChangeAvatar();
                avatarTexture.dispose();
                avatarTexture = new Texture(App.getApp().getLoggedInUser().getAvatarAddress());
                avatarImage.setDrawable(new TextureRegionDrawable(new TextureRegion(avatarTexture)));
            }
        });

        saveDataButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                alert.setText(controller.saveProfile());
            }
        });

        deleteAccountButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (deleteAccountButton.getText().toString().equals("Confirm ...")) {
                    controller.deleteAccount();
                } else {
                    deleteAccountButton.setText("Confirm ...");
                }
            }
        });
    }

    public void selectUserAvatar(String selectedAvatar) {
        User user = App.getApp().getLoggedInUser();
        user.setAvatar(Avatar.getAvatarByName(selectedAvatar));
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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

    public TextField getChangeUserNameField() {
        return changeUserNameField;
    }

    public TextField getChangePasswordField() {
        return changePasswordField;
    }
}

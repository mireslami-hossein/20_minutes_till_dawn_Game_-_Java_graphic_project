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
import com.badlogic.gdx.utils.viewport.FitViewport;
import untilDown.com.Main;
import untilDown.com.Models.App;
import untilDown.com.Models.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoreboardView implements Screen {
    private Stage stage;
    private Skin skin;

    private Table mainTable;         // For overall screen layout
    private Table dataContentTable;  // For headers and user data rows to ensure alignment
    private SelectBox<SortType> sortTypeBox;
    private TextButton back;

    private enum SortType { SCORE, USERNAME, KILL, SURVIVAL_TIME }
    private SortType currentSort = SortType.SCORE;
    private List<User> users;

    public ScoreboardView(Skin skin) {
        this.skin = skin;
        this.users = new ArrayList<>(App.getApp().getAllUsers()); // It's good practice to copy
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(1200, 1280));
        Gdx.input.setInputProcessor(stage);

        mainTable = new Table();
        mainTable.top();
        mainTable.setFillParent(true);

        Label gameHeader = new Label(App.getApp().getGameTitle(), skin);
        gameHeader.setColor(Color.RED);
        gameHeader.setFontScale(4f);
        mainTable.add(gameHeader).colspan(5).center().padTop(75).padBottom(50);
        mainTable.row();

        Label titleLabel = new Label("Top 10 Users", skin); // Renamed for clarity
        titleLabel.setFontScale(2f);
        titleLabel.setColor(Color.GREEN);
        mainTable.add(titleLabel).colspan(5).center().padBottom(30);
        mainTable.row();

        Table sortControlsTable = new Table();
        Label sortLabel = new Label("Sort By ", skin);
        sortLabel.setFontScale(1.5f);
        sortLabel.setColor(Color.GREEN);
        sortControlsTable.add(sortLabel).padRight(10); // Adjusted padding

        sortTypeBox = new SelectBox<>(skin);
        sortTypeBox.setItems(SortType.values());
        sortTypeBox.setSelected(currentSort); // Initialize with currentSort
        sortTypeBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                currentSort = sortTypeBox.getSelected();
                updateUsersDataRows(); // Call the method to update data rows
            }
        });
        sortControlsTable.add(sortTypeBox).width(App.fieldWidth > 0 ? App.fieldWidth : 200); // Ensure fieldWidth is positive
        mainTable.add(sortControlsTable).colspan(5).center().padBottom(30);
        mainTable.row();

        dataContentTable = new Table().debugTable(); // Enable debug lines for this table
        mainTable.add(dataContentTable).colspan(5).expandX().fillX().padTop(20); // dataContentTable spans 5 cols, expands and fills
        mainTable.row();

        rebuildDataContentTable();


        back = new TextButton("Back", skin);
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().navigateToMainMenu();
            }
        });
        mainTable.add(back).width(App.fieldWidth > 0 ? App.fieldWidth : 200).colspan(5).center().padTop(30);

        stage.addActor(mainTable);
    }

    private void addHeaders() {
        Label profileHeader = new Label("Profile", skin);
        Label usernameHeader = new Label("Username", skin);
        Label scoreHeader = new Label("Score", skin);
        Label killsHeader = new Label("Kills", skin);
        Label survivalTimeHeader = new Label("Survival Time", skin);

        float headerFontScale = 1.5f;
        profileHeader.setFontScale(headerFontScale);
        usernameHeader.setFontScale(headerFontScale);
        scoreHeader.setFontScale(headerFontScale);
        killsHeader.setFontScale(headerFontScale);
        survivalTimeHeader.setFontScale(headerFontScale);

        float headerPad = 20f; // Adjusted padding

        dataContentTable.add(profileHeader).expandX().center();
        dataContentTable.add(usernameHeader).expandX().center();
        dataContentTable.add(scoreHeader).expandX().center();
        dataContentTable.add(killsHeader).expandX().center();
        dataContentTable.add(survivalTimeHeader).expandX().center();
        dataContentTable.row();
    }

    private void updateUsersDataRows() {
        // This method now only updates user rows, assuming headers are managed by rebuildDataContentTable
        rebuildDataContentTable();
    }

    private void rebuildDataContentTable() {
        dataContentTable.clearChildren(); // Clear all previous content (headers and rows)
        addHeaders();

        users.sort(getComparator());

        float userDataPadding = 10f; // Padding for user data cells
        float imageSize = 50f;
        float loggedInUserImageSize = 75f; // Example: larger image for logged-in user

        for (int i = 0; i < Math.min(10, users.size()); i++) {
            User user = users.get(i);

            // It's important to create new Texture and Image instances if user.getAvatarAddress() can change
            // or if textures need to be managed (e.g., disposed). For simplicity, assuming it's handled.
            Texture profileTexture = new Texture(user.getAvatarAddress()); // Ensure path is correct
            Image profileImage = new Image(profileTexture);

            Label nameLabel = new Label(user.getUsername(), skin);
            Label scoreLabel = new Label(String.valueOf(user.getScore()), skin);
            Label killsLabel = new Label(String.valueOf(user.getKills()), skin);
            Label timeLabel = new Label(formatTime(user.getTimeLived()), skin);

            if (i < 3) { // Top 3 users styling
                nameLabel.setColor(Color.GOLD);
                scoreLabel.setColor(Color.GOLD);
                killsLabel.setColor(Color.GOLD);
                timeLabel.setColor(Color.GOLD);
            }

            float currentImageSize = imageSize;
            if (user.equals(App.getApp().getLoggedInUser())) {
                currentImageSize = loggedInUserImageSize; // Use larger size
                // Apply font scaling for logged-in user's labels
                float loggedInFontScale = 1.5f;
                nameLabel.setFontScale(loggedInFontScale);
                scoreLabel.setFontScale(loggedInFontScale);
                killsLabel.setFontScale(loggedInFontScale);
                timeLabel.setFontScale(loggedInFontScale);
            }

            dataContentTable.add(profileImage).size(currentImageSize, currentImageSize).pad(userDataPadding).center();
            dataContentTable.add(nameLabel).pad(userDataPadding).expandX().center();
            dataContentTable.add(scoreLabel).pad(userDataPadding).expandX().center();
            dataContentTable.add(killsLabel).pad(userDataPadding).expandX().center();
            dataContentTable.add(timeLabel).pad(userDataPadding).expandX().center();
            dataContentTable.row();
        }
    }


    private Comparator<User> getComparator() {
        switch (currentSort) {
            case USERNAME:
                return Comparator.comparing(User::getUsername);
            case KILL:
                return Comparator.comparingInt(User::getKills).reversed();
            case SURVIVAL_TIME:
                return Comparator.comparingInt(User::getTimeLived).reversed();
            default: // SCORE
                return Comparator.comparingInt(User::getScore).reversed();
        }
    }

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);


        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        if (stage != null) {
            stage.dispose();
        }

        App.getApp().saveUsers(); // This was in your original code.
    }
}

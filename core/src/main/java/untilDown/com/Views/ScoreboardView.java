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
    private Table table;
    private TextButton back;
    private Skin skin;

    private enum SortType { SCORE, USERNAME, KILL, SURVIVAL_TIME }

    private SortType currentSort = SortType.SCORE;
    private List<User> users;

    public ScoreboardView(Skin skin) {
        this.skin = skin;
        this.users = new ArrayList<>(App.getApp().getAllUsers());
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(1200, 1280));
        Gdx.input.setInputProcessor(stage);
        updateTable();

        stage.addActor(table);
    }

    private void setTableHeader() {
        Label gameHeader = new Label(App.getApp().getGameTitle(), skin);
        gameHeader.setColor(Color.RED);
        gameHeader.setFontScale(4f);
        table.add(gameHeader).colspan(5).center().padTop(75).padBottom(50);
        table.row();

        Label title = new Label("Top 10 Users", skin);
        title.setFontScale(2f);
        title.setColor(Color.GREEN);
        table.add(title).colspan(5).padBottom(30);
        table.row();

        Label profileHeader = new Label("Profile", skin);
        Label usernameHeader = new Label("Username", skin);
        Label scoreHeader = new Label("Score", skin);
        Label killsHeader = new Label("Kills", skin);
        Label survivalTimeHeader = new Label("Survival Time", skin);

        profileHeader.setFontScale(1.5f);

        usernameHeader.setFontScale(1.5f);

        scoreHeader.setFontScale(1.5f);

        killsHeader.setFontScale(1.5f);

        survivalTimeHeader.setFontScale(1.5f);


        table.add(profileHeader).pad(25);
        table.add(usernameHeader).pad(25);
        table.add(scoreHeader).pad(25);
        table.add(killsHeader).pad(25);
        table.add(survivalTimeHeader).pad(25);
        table.row();
    }

    private void updateTable() {
        table = new Table();

        table.setFillParent(true);
        table.top().padTop(50);

        setTableHeader();

        users.sort(getComparator());

        for (int i = 0; i < Math.min(10, users.size()); i++) {
            User user = users.get(i);
            table.row().pad(5);
            Texture profile = new Texture(user.getAvatarAddress());
            Image profileImage = new Image(profile);

            Label name = new Label(user.getUsername(), skin);
            Label score = new Label(String.valueOf(user.getScore()), skin);
            Label kills = new Label(String.valueOf(user.getKills()), skin);
            Label time = new Label(formatTime(user.getTimeLived()), skin);

            if (i < 3) {
                name.setColor(Color.GOLD);
                score.setColor(Color.GOLD);
                kills.setColor(Color.GOLD);
                time.setColor(Color.GOLD);

            }
            if (user.equals(App.getApp().getLoggedInUser())) {
                profileImage.setScale(1.5f);
                name.setFontScale(1.5f);
                score.setFontScale(1.5f);
                kills.setFontScale(1.5f);
                time.setFontScale(1.5f);
            }


            table.add(profileImage).size(50,50).pad(5).center();
            table.add(name);
            table.add(score);
            table.add(kills);
            table.add(time);
        }

        back = new TextButton("Back", skin);
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().navigateToMainMenu();
            }
        });
        table.row();
        table.add();
        table.add(back).width((float)App.fieldWidth).colspan(3).center().padTop(30);
    }

    private Comparator<User> getComparator() {
        switch (currentSort) {
            case USERNAME :
                return Comparator.comparing(User::getUsername);
            case KILL :
                return Comparator.comparingInt(User::getKills).reversed();
            case SURVIVAL_TIME :
                return Comparator.comparingInt(User::getTimeLived).reversed();
            default :
                return Comparator.comparingInt(User::getScore).reversed();
        }
    }

    private String formatTime(int seconds) {
        int min = seconds / 60;
        int sec = seconds % 60;
        return String.format("%d:%02d", min, sec);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override public void resize(int width, int height) {}

    @Override public void pause() {}

    @Override public void resume() {}

    @Override public void hide() {}

    @Override public void dispose() {
        if (stage != null) {
            stage.dispose();
        }
        App.getApp().saveUsers();
    }

}

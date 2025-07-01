package untilDown.com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import untilDown.com.Controllers.*;
import untilDown.com.Models.App;
import untilDown.com.Models.GameAssetManager;
import untilDown.com.Views.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;

    private static SpriteBatch batch;

    // View screens
    private SignupMenuView signupMenuView;
    private LoginMenuView loginMenuView;
    private ForgetPassView forgetPassView;

    private MainMenuView mainMenuView;
    private ProfileMenuView profileMenuView;
    private ScoreboardView scoreboardView;
    private HintMenuView hintMenuView;
    private PreGameMenuView preGameMenuView;
    private GameView gameView;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();

        navigateToSignupMenu();
//        navigateToGame();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        App.getApp().saveUsers();
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static Main getMain() {
        return main;
    }

    // get views
    public SignupMenuView getSignupMenuView() {
        return signupMenuView;
    }

    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }

    // change screens
    public void navigateToSignupMenu() {
        signupMenuView = new SignupMenuView(new SignupMenuController(), GameAssetManager.getManager().getSkin());
        setScreen(signupMenuView);
    }

    public void navigateToLoginMenu() {
        loginMenuView = new LoginMenuView(new LoginMenuController(), GameAssetManager.getManager().getSkin());
        setScreen(loginMenuView);
    }
    public void navigateToForgetPass() {
        forgetPassView = new ForgetPassView(loginMenuView.getController(), GameAssetManager.getManager().getSkin());
        setScreen(forgetPassView);
    }

    public void navigateToMainMenu() {
        mainMenuView = new MainMenuView(new MainMenuController(), GameAssetManager.getManager().getSkin());
        setScreen(mainMenuView);
    }

    public void navigateToProfileMenu() {
        profileMenuView = new ProfileMenuView(mainMenuView.getController(), GameAssetManager.getManager().getSkin());
        setScreen(profileMenuView);
    }

    public void navigateToHintMenu() {
        hintMenuView = new HintMenuView(new PreGameMenuController(), GameAssetManager.getManager().getSkin());
        setScreen(hintMenuView);
    }

    public void navigateToScoreboard() {
        scoreboardView = new ScoreboardView(GameAssetManager.getManager().getSkin());
        setScreen(scoreboardView);
    }

    public void navigateToPregameMenu() {
        preGameMenuView = new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getManager().getSkin());
        setScreen(preGameMenuView);
    }
    public void navigateToGame() {
        gameView = new GameView(new GameController(), GameAssetManager.getManager().getSkin());
        setScreen(gameView);
    }
}

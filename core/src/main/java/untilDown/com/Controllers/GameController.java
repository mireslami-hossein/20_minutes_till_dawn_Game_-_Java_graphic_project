package untilDown.com.Controllers;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import untilDown.com.Main;
import untilDown.com.Models.Player;
import untilDown.com.Views.GameView;

public class GameController {
    private GameView view;

    private PlayerController playerController;
    private WeaponController weaponController;
    private WorldController worldController;

    private Camera camera;
    private Player player = new Player();


    public void setView(GameView view) {
        this.view = view;

        playerController = new PlayerController(player);
//        worldController = new WorldController(playerController);
        weaponController = new WeaponController(player);
    }

    public void updateGame(float delta) {
        if (view != null) {
            playerController.updateGame();
            weaponController.updateGame(delta);
//            worldController.updateGame();
        }
    }

    // Camera
    public Camera getCamera() {
        return camera;
    }

    public void setCamera() {
        camera = new OrthographicCamera();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        weaponController.setCamera(camera);
    }

    public void updateCamera() {
        camera.position.set(player.getPosition().x + player.getPlayerSprite().getWidth()/2, player.getPosition().y + player.getPlayerSprite().getHeight()/2, 0);
        camera.update();
        Main.getBatch().setProjectionMatrix(camera.combined);
    }

    // Player
    public Player getPlayer() {
        return player;
    }


    public WeaponController getWeaponController() {
        return weaponController;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }
}

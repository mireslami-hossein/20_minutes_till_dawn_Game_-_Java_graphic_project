package untilDown.com.Controllers;

import untilDown.com.Models.Player;
import untilDown.com.Views.GameView;

public class GameController {
    private GameView view;

    private PlayerController playerController;
    private WeaponController weaponController;
    private WorldController worldController;


    public void setView(GameView view) {
        this.view = view;

        playerController = new PlayerController(new Player());
        worldController = new WorldController(playerController);
        weaponController = new WeaponController();
    }

    public void updateGame() {
        if (view != null) {
            playerController.updateGame();
            weaponController.updateGame();
            worldController.updateGame();
        }
    }
}

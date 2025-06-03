package untilDown.com.Controllers;

import untilDown.com.Models.App;
import untilDown.com.Models.Player;
import untilDown.com.Views.GameView;

public class GameController {
    private GameView view;

    private PlayerController playerController;
    private WeaponController weaponController;
    private WorldController worldController;


    public void setView(GameView view) {
        this.view = view;

//        Player player = App.getApp().getLoggedInUser().getPlayer();
//        playerController = new PlayerController(player);
//        worldController = new WorldController(playerController);
//        weaponController = new WeaponController();
    }

//    public void updateGame() {
//        if (view != null) {
////            playerController.updateGame();
//            weaponController.updateGame();
//            worldController.updateGame();
//        }
//    }
}

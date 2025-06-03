package untilDown.com.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import untilDown.com.Main;
import untilDown.com.Models.GameAssetManager;
import untilDown.com.Models.Player;

public class PlayerController {
    private Player player;

    public PlayerController(Player player) {
        this.player = player;
    }

    public void updateGame() {
        player.getPlayerSprite().draw(Main.getBatch());

//        if (player.isPlayerIdle()) {
            idleAnimation();
//        }

//        handlePlayerInput();
    }
//
    public void idleAnimation() {
        Animation<Texture> animation = GameAssetManager.getManager().getPlayer1_animation();
        animation.setPlayMode(Animation.PlayMode.LOOP);

        player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));
    }
//
//    public void handlePlayerInput() {
//        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            player.addToPosition(0, -player.getSpeed());
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//            player.addToPosition(0, player.getSpeed());
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
//            player.addToPosition(player.getSpeed(), 0);
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            player.addToPosition(-player.getSpeed(), 0);
//            player.getPlayerSprite().flip(true, false);
//        }
//    }
//
    public Player getPlayer() {
        return player;
    }
}

package untilDown.com.Controllers;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import untilDown.com.Main;
import untilDown.com.Models.GameAssetManager;
import untilDown.com.Models.GameKeysManager;
import untilDown.com.Models.Player;

public class PlayerController {
    private Player player;

    int up = GameKeysManager.getManager().getGameKeys().get("goUp");
    int down = GameKeysManager.getManager().getGameKeys().get("goDown");
    int left = GameKeysManager.getManager().getGameKeys().get("goLeft");
    int right = GameKeysManager.getManager().getGameKeys().get("goRight");

    public PlayerController(Player player) {
        this.player = player;
    }

    public void updateGame() {
        drawPlayer();

//        if (player.isPlayerIdle()) {
//            idleAnimation();
//        }

        handlePlayerInput();
    }

    private void drawPlayer() {
        Sprite playerSprite = player.getPlayerSprite();
        playerSprite.setPosition(player.getPosition().x, player.getPosition().y);
        player.getPlayerSprite().draw(Main.getBatch());
    }

    public void idleAnimation() {
        Animation<Texture> animation = GameAssetManager.getManager().getPlayer1_animation();
        animation.setPlayMode(Animation.PlayMode.LOOP);

        player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));
    }
//
    public void handlePlayerInput() {
        if (Gdx.input.isKeyPressed(up)) {
            player.changePlayerPosition(0, player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(down)) {
            player.changePlayerPosition(0, -player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(right)) {
            player.changePlayerPosition(player.getSpeed(), 0);
        }
        if (Gdx.input.isKeyPressed(left)) {
            player.changePlayerPosition(-player.getSpeed(), 0);
//            player.getPlayerSprite().flip(true, false);
        }
    }
//
    public Player getPlayer() {
        return player;
    }
}

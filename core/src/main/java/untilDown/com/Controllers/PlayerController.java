package untilDown.com.Controllers;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import untilDown.com.Main;
import untilDown.com.Models.GameKeysManager;
import untilDown.com.Models.Player;

public class PlayerController {
    private Player player;

    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> runAnimation;
    private Animation<TextureRegion> walkAnimation;

    private float stateTime = 0f;

    public PlayerController(Player player) {
        this.player = player;
        loadAnimation();
    }

    public void loadAnimation() {
        idleAnimation = player.getHeroType().getIdleAnimation();
        idleAnimation.setPlayMode(Animation.PlayMode.LOOP);

        runAnimation = player.getHeroType().getRunAnimation();
        runAnimation.setPlayMode(Animation.PlayMode.LOOP);

        walkAnimation = player.getHeroType().getWalkAnimation();
        walkAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }


    public void updateGame() {
        stateTime += Gdx.graphics.getDeltaTime();
        setPlayerAnimation();
        drawPlayer();
        handlePlayerInput();
    }

    public void setPlayerAnimation() {
        Player.PlayerState state = player.getPlayerState();
        if (state.equals(Player.PlayerState.Idle)) {
            idleAnimation();
        } else if (state.equals(Player.PlayerState.Walking)) {
            walkAnimation();
        } else {
            runAnimation();
        }
    }

    private void drawPlayer() {
        Sprite playerSprite = player.getPlayerSprite();
        playerSprite.setPosition(player.getPosition().x, player.getPosition().y);
        playerSprite.draw(Main.getBatch());
    }

    public void idleAnimation() {
        player.getPlayerSprite().setRegion(idleAnimation.getKeyFrame(stateTime, true));
    }

    public void walkAnimation() {
        player.getPlayerSprite().setRegion(walkAnimation.getKeyFrame(stateTime, true));
    }

    public void runAnimation() {
        player.getPlayerSprite().setRegion(runAnimation.getKeyFrame(stateTime, true));
    }


    public void handlePlayerInput() {
        player.setPlayerState(Player.PlayerState.Idle);
        int up = GameKeysManager.getManager().getKey("goUp");
        int down = GameKeysManager.getManager().getKey("goDown");
        int left = GameKeysManager.getManager().getKey("goLeft");
        int right = GameKeysManager.getManager().getKey("goRight");

        if (Gdx.input.isKeyPressed(up)) {
            walkTo(0, player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(down)) {
            walkTo(0, -player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(right)) {
            walkTo(player.getSpeed(), 0);
        }
        if (Gdx.input.isKeyPressed(left)) {
            walkTo(-player.getSpeed(), 0);
            player.getPlayerSprite().setFlip(true, false);
        }

    }

    public void walkTo(int x, int y) {
        player.changePlayerPosition(x, y);
        player.setPlayerState(Player.PlayerState.Walking);
    }
}

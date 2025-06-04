package untilDown.com.Controllers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import untilDown.com.Main;
import untilDown.com.Models.Player;

public class WeaponController {
    Player player;

    public WeaponController(Player player) {
        this.player = player;
    }

    public void updateGame() {
        Sprite gunSprite = player.getGun().getSprite();
        gunSprite.setPosition(player.getPosition().x, player.getPosition().y);
        gunSprite.draw(Main.getBatch());
    }

    public void handleWeaponRotation(float targetWorldX, float targetWorldY) {
        Sprite gunSprite = player.getGun().getSprite();

        float deltaX = targetWorldX - player.getPosition().x;
        float deltaY = targetWorldY - player.getPosition().y;

        float angleRadians = MathUtils.atan2(deltaY, deltaX);

        if (targetWorldX < player.getPosition().x) {
            gunSprite.setFlip(true, false);
            gunSprite.setRotation((float) (angleRadians - Math.PI) * MathUtils.radiansToDegrees);

        } else {
            gunSprite.setFlip(false, false);
            gunSprite.setRotation(angleRadians * MathUtils.radiansToDegrees);

        }
    }
}

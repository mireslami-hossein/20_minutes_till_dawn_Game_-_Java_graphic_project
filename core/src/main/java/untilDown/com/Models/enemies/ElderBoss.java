package untilDown.com.Models.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import untilDown.com.Models.GameAssetManager;
import untilDown.com.Models.Player;

public class ElderBoss extends Enemy {
    float time = 0f;
    float speed = 0.1f;
    float dashSpeed = 4f;

    float dashTimer = 0f;
    float dashDuration = 0.5f;
    boolean isDashing = false;

    Texture elderBoss;

    public ElderBoss(Vector2 position) {
        super(400, position);

        sprite = new Sprite();
        sprite.setPosition(position.x, position.y);
        elderBoss = GameAssetManager.getManager().getElder();
    }

    @Override
    public void update(float delta, Player player) {
        time += delta;
        dashTimer += delta;
        if (dashTimer >= 5f) {
            isDashing = true;
        }

        if (isDashing) {
            dashDuration += delta;
            if (dashDuration >= 0.5f) {
                dashDuration = 0f;
                isDashing = false;
            }
        }
        goTo(player);

        sprite.setRegion(elderBoss);
        sprite.setPosition(position.x, position.y);
    }

    private void goTo(Player player) {
        float speed = isDashing ? dashSpeed : this.speed;
        Vector2 direction = new Vector2(player.getPosition()).sub(position).nor().scl(speed);
        position.add(direction.scl(speed));
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }
}

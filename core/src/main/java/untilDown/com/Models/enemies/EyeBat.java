package untilDown.com.Models.enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import untilDown.com.Controllers.EnemyController;
import untilDown.com.Models.Bullet;
import untilDown.com.Models.GameAssetManager;
import untilDown.com.Models.Player;

public class EyeBat extends Enemy {
    float time = 0f;
    float shootingTimer = 0f;
    int speed = 3;

    Animation<TextureRegion> eyeBatAnimation;

    public EyeBat(Vector2 position) {
        super(50, position);

        sprite = new Sprite();
        sprite.setPosition(position.x, position.y);
        eyeBatAnimation = GameAssetManager.getManager().getTreeAnimation();
    }

    @Override
    public void update(float delta, Player player) {
        time += delta;
        shootingTimer += delta;
        if (shootingTimer >= 3f) {
            shootingTimer = 0f;
            shootAt(player);
        }

        Vector2 direction = new Vector2(player.getPosition()).sub(position).nor();
        position.add(direction.scl(speed));

        sprite.setRegion(eyeBatAnimation.getKeyFrame(time, true));
        sprite.setPosition(position.x, position.y);
    }

    private void shootAt(Player player) {
        Vector2 direction = new Vector2(player.getPosition()).sub(position).nor().scl(5f);
        Bullet enemyBullet = new Bullet(position.x, position.y, direction, 1);
        EnemyController.addBullet(enemyBullet);
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }
}

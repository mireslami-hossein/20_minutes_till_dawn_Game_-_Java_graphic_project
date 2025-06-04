package untilDown.com.Models.enemies;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import untilDown.com.Models.GameAssetManager;
import untilDown.com.Models.Player;

public class Tentacle extends Enemy {
    float time = 0f;
    int speed = 5;
    Animation<TextureRegion> tentacleAnimation;

    public Tentacle(Vector2 position) {
        super(25, position);

        sprite = new Sprite();
        sprite.setPosition(position.x, position.y);
        tentacleAnimation = GameAssetManager.getManager().getTentacleAnimation();
    }

    @Override
    public void update(float delta, Player player) {
        Vector2 direction = new Vector2(player.getPosition()).sub(position).nor();
        position.add(direction.scl(speed));

        time += delta;

        sprite.setRegion(tentacleAnimation.getKeyFrame(time, true));
        sprite.setPosition(position.x, position.y);
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }
}

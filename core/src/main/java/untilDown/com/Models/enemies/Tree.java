package untilDown.com.Models.enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import untilDown.com.Models.GameAssetManager;
import untilDown.com.Models.Player;

public class Tree extends Enemy {
    float time = 0f;
    Animation<TextureRegion> treeAnimation;

    public Tree(Vector2 position) {
        super(250, position);

        treeAnimation = GameAssetManager.getManager().getTreeAnimation();
        sprite = new Sprite(treeAnimation.getKeyFrame(0));
        sprite.setPosition(position.x, position.y);
    }

    @Override
    public void update(float delta, Player player) {
        time += delta;

        sprite.setRegion(treeAnimation.getKeyFrame(time, true));
        sprite.setPosition(position.x, position.y);
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }
}

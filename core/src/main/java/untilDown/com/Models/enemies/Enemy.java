package untilDown.com.Models.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import untilDown.com.Models.CollisionRect;
import untilDown.com.Models.Player;

public abstract class Enemy {
    private CollisionRect collisionRect;
    private int hp;
    protected Sprite sprite;
    protected Vector2 position;

    public Enemy(int hp, Vector2 position) {
        this.hp = hp;
        this.position = position;
    }

    public abstract void update(float delta, Player player);
    public abstract void render(SpriteBatch batch);

    public void damage(int amount) {
        hp -= amount;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public Vector2 getPosition() {
        return position;
    }
    public void addPosition(Vector2 position) {
        this.position.add(position);
    }
}

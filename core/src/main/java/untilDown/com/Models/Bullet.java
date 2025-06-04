package untilDown.com.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Vector2 velocity;

    private Texture texture;
    private Sprite sprite;

    private CollisionRect collisionRect;

    private int damage;

    public Bullet(float x, float y, Vector2 velocity,int damage) {
        this.velocity = velocity;
        this.damage = damage;

        texture = GameAssetManager.getManager().getBullet();
        sprite = new Sprite(texture);
        sprite.setSize(20 , 20);
        sprite.setPosition(x, y);

        collisionRect = new CollisionRect(x, y, sprite.getWidth(), sprite.getHeight());
    }

    public void addToPositionInATime() {
        sprite.setPosition(sprite.getX() + velocity.x, sprite.getY() + velocity.y);
    }

    public Sprite getSprite() {
        return sprite;
    }
}

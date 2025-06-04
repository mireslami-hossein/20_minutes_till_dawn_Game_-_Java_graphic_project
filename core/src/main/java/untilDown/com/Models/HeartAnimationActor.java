package untilDown.com.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class HeartAnimationActor extends Actor {
    private Animation<TextureRegion> animation;
    private float stateTime = 0f;
    private float width, height;

    public HeartAnimationActor(Texture[] textures, float x, float y, float width, float height, float frameDuration) {
        Array<TextureRegion> frames = new Array<>();
        for (Texture texture : textures) {
            frames.add(new TextureRegion(texture));
        }

        this.animation = new Animation<>(frameDuration, frames, Animation.PlayMode.LOOP);
        this.width = width;
        this.height = height;
        setPosition(x, y);
        setSize(width, height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, getX(), getY(), width, height);
    }
}

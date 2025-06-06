package untilDown.com.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import untilDown.com.Main;
import untilDown.com.Models.Bullet;
import untilDown.com.Models.GameKeysManager;
import untilDown.com.Models.Player;
import untilDown.com.Models.Setting;
import untilDown.com.Models.gun.PlayerGun;

import java.util.ArrayList;
import java.util.Iterator;

public class WeaponController {
    Player player;
    ArrayList<Bullet> bullets = new ArrayList<>();
    Camera camera;

    float reloadTimer = 0f;

    public WeaponController(Player player) {
        this.player = player;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void updateGame(float delta) {
        Sprite gunSprite = player.getGun().getSprite();
        gunSprite.setPosition(player.getPosition().x, player.getPosition().y);
        gunSprite.draw(Main.getBatch());

        updateBullets();
        handleReload(delta);
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

    public void handleShoot(float targetXInWorld, float targetYInWorld) {
        PlayerGun gun = player.getGun();
        if (gun.getGunState() == PlayerGun.GunState.Reloading) return;

        Vector2 velocity = new Vector2();
        velocity.x = targetXInWorld- player.getPosition().x;
        velocity.y = targetYInWorld - player.getPosition().y;
        velocity.nor();

        velocity.setLength(15f);
        if (gun.getCurrentAmmo() == 0) {
            return;
        } else {
            gun.decreaseCurrentAmmo(1);
        }
        int numOfProjectiles = gun.getCurrentProjectilesPerShot();
        float spreadAngle = 8f;
        float totalSpreadAngle = spreadAngle * numOfProjectiles;
        float startAngle = totalSpreadAngle / 2;
        for (int i = 0; i < numOfProjectiles; i++) {
            float angleOffset = startAngle + i * spreadAngle;
            Vector2 projectileVelocity = new Vector2(velocity).rotateDeg(angleOffset);
            bullets.add(new Bullet(player.getPosition().x, player.getPosition().y, projectileVelocity, player.getGun().getCurrentDamage()));
        }
    }

    public void updateBullets() {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.addToPositionInATime();
            float x = bullet.getSprite().getX();
            float y = bullet.getSprite().getY();

            float cameraMinX = camera.position.x - camera.viewportWidth / 2;
            float cameraMaxX = camera.position.x + camera.viewportWidth / 2;
            float cameraMinY = camera.position.y - camera.viewportHeight / 2;
            float cameraMaxY = camera.position.y + camera.viewportHeight / 2;

            // اگر گلوله خارج از محدوده صفحه بود، حذفش کن
            if (x < cameraMinX || x > cameraMaxX || y < cameraMinY || y > cameraMaxY) {
                iterator.remove();  // این روش امن‌تر از removeAll هست
            } else {
                bullet.getSprite().draw(Main.getBatch());
            }
        }
    }

    private void handleReload(float delta) {
        PlayerGun gun = player.getGun();
        PlayerGun.GunState gunState = gun.getGunState();
        float reloadingTime = player.getGun().getCurrentReloadTime();
        if (Gdx.input.isKeyPressed(GameKeysManager.getManager().getKey("reload")) ||
            (gun.getCurrentAmmo() == 0 && Setting.autoReloadEnabled)) {
            if (gun.getCurrentAmmo() < gun.getCurrentMaxAmmo())
                gun.setGunState(PlayerGun.GunState.Reloading);
        }
        if (gunState == PlayerGun.GunState.Reloading) {
            if (reloadTimer >= reloadingTime) {
                reloadTimer = 0f;
                gun.setGunState(PlayerGun.GunState.Still);
                gun.fullAmmo();
            } else {
                // TODO: play animation
                reloadTimer += delta;
            }
        }

    }
}

package untilDown.com.Models.gun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerGun {
    private GunType type;
    private Sprite gunSprite;

    private int currentDamage;
    private int currentProjectilesPerShot;
    private float currentReloadTime;

    private int currentMaxAmmo;
    private int currentAmmo;

    public enum GunState {
        Still, Reloading
    }
    private GunState gunState = GunState.Still;

    public PlayerGun(GunType gunType) {
        this.type = gunType;

        this.gunSprite = new Sprite(new Texture(type.getStillPath()));
        this.gunSprite.setScale(1.5f);
        this.currentDamage = gunType.getDamage();
        this.currentProjectilesPerShot = gunType.getProjectileNumberPerShot();
        this.currentReloadTime = gunType.getTimeReload();
        this.currentMaxAmmo = gunType.getMaxAmmo();
        this.currentAmmo = this.currentMaxAmmo;
    }


    public GunType getType() {
        return type;
    }

    public int getCurrentAmmo() {
        return currentAmmo;
    }

    public void decreaseCurrentAmmo(int ammo) {
        this.currentAmmo -= ammo;
        if (this.currentAmmo < 0) {
            this.currentAmmo = 0;
        }
    }

    public void fullAmmo() {
        this.currentAmmo = type.getMaxAmmo();
    }

    public Sprite getSprite() {
        return gunSprite;
    }

    public float getCurrentReloadTime() {
        return currentReloadTime;
    }

    public int getCurrentDamage() {
        return currentDamage;
    }

    public int getCurrentMaxAmmo() {
        return currentMaxAmmo;
    }

    public int getCurrentProjectilesPerShot() {
        return currentProjectilesPerShot;
    }

    public GunState getGunState() {
        return gunState;
    }

    public void setGunState(GunState gunState) {
        this.gunState = gunState;
    }

    public boolean isReloading() {
        return gunState == GunState.Reloading;
    }
}

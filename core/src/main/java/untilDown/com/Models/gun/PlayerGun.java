package untilDown.com.Models.gun;

public class PlayerGun {
    private GunType type;
    private int currentDamage;
    private int currentProjectilesPerShot;
    private float currentReloadTimeSeconds;

    private int currentMaxAmmo;
    private int currentAmmo;

    public PlayerGun(GunType gunType) {
        this.type = gunType;
        this.currentDamage = gunType.getDamage();
        this.currentProjectilesPerShot = gunType.getProjectileNumberPerShot();
        this.currentReloadTimeSeconds = gunType.getTimeReload();
        this.currentMaxAmmo = gunType.getMaxAmmo();
        this.currentAmmo = this.currentMaxAmmo;
    }


    public GunType getType() {
        return type;
    }
}

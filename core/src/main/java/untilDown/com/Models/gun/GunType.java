package untilDown.com.Models.gun;


public enum GunType {
    Revolver(20,1,1,6),
    Shotgun(10,4,1,2),
    SMG(8,1,2,24);

    private int damage;
    private int projectileNumberPerShot;
    private int timeReload;
    private int maxAmmo;

    GunType(int damage, int projectileNumberPerShot, int timeReload, int maxAmmo) {
        this.damage = damage;
        this.projectileNumberPerShot = projectileNumberPerShot;
        this.timeReload = timeReload;
        this.maxAmmo = maxAmmo;
    }

    public String getStillPath() {
        return "guns/" + this.name() + "/" + this.name() + "Still.png";
    }

//    public Animation getReloadAnimationPath() {
//        return "guns/" + this.name() + "Still.png";
//    }

    public int getDamage() {
        return damage;
    }

    public int getProjectileNumberPerShot() {
        return projectileNumberPerShot;
    }

    public int getTimeReload() {
        return timeReload;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }
}

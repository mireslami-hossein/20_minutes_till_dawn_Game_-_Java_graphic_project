package untilDown.com.Models;

public enum AbilityType {
    Vitality("Increases max HP by 1 unit."),
    Damager("Increases weapon damage by 25% for 10 seconds."),
    Procrease("Increases weapon Projectile by 1 unit."),
    Ammocrease("Increases maximum weapon ammo by 5 units."),
    Speedy("2x player movement speed for 10 seconds.");

    private final String hintText;

    AbilityType(String hintText) {
        this.hintText = hintText;
    }

    public String getHintText() {
        return hintText;
    }
}

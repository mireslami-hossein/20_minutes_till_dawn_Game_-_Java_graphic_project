package untilDown.com.Models.hero;

public class Hero {
    private HeroType heroType;

    private int currentHP;
    private int currentSpeed;

    public Hero(HeroType heroType) {
        this.heroType = heroType;

        this.currentHP = heroType.getMaxHp();
        this.currentSpeed = heroType.getSpeed();
    }

    public HeroType getType() {
        return heroType;
    }
}

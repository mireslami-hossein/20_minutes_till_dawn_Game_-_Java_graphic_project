package untilDown.com.Models.hero;

public enum HeroType {
    Shana(4,4),
    Diamond(7,1),
    Scarlet(3,5),
    Lilith(5,3),
    Dasher(2,10);

    private int maxHp;
    private int speed;

    HeroType(int maxHp, int speed) {
        this.maxHp = maxHp;
        this.speed = speed;
    }

    public String getPath() {
        return "heroes/portraits/" + this.name() + "_Portrait.png";
    }

    public int getSpeed() {
        return speed;
    }

    public int getMaxHp() {
        return maxHp;
    }
}

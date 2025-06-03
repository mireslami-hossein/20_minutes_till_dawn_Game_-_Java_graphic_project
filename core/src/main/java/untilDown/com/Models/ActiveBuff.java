package untilDown.com.Models;

public class ActiveBuff {
    public enum BuffType {
        DAMAGE_BOOST,
        SPEED_BOOST
    }
    private BuffType type;
    private float durationRemaining;
    private float magnitude;
    private boolean isMultiplier;

    public ActiveBuff(BuffType type, float duration, float magnitude, boolean isMultiplier) {
        this.type = type;
        this.durationRemaining = duration;
        this.magnitude = magnitude;
        this.isMultiplier = isMultiplier;
    }


    public void update(float deltaTime) {
        this.durationRemaining -= deltaTime;
    }

    public boolean isExpired() {
        return this.durationRemaining <= 0;
    }


    public BuffType getType() { return type; }
    public float getMagnitude() { return magnitude; }
    public boolean isMultiplier() { return isMultiplier; }
}

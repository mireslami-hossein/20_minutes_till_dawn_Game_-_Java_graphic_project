package untilDown.com.Models;

public enum Avatar {
    Man_1, Man_2, Man_3,
    Man_4, Man_5, Man_6,;

    public static Avatar getRandomAvatar() {
        return Avatar.values()[(int) (Math.random()*Avatar.values().length)];
    }

    public String getAddress() {
        return ("avatar/" + (ordinal() + 1) + ".png");
    }
}

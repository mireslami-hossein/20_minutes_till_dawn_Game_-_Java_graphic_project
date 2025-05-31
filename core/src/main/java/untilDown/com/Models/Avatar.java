package untilDown.com.Models;

public enum Avatar {
    Man_1, Man_2, Man_3,
    Woman_1, Woman_2, Woman_3;

    public static Avatar getRandomAvatar() {
        return Avatar.values()[(int) (Math.random()*Avatar.values().length)];
    }
}

package untilDown.com.Models.avatar;

import com.badlogic.gdx.utils.Array;

public enum Avatar implements AvatarImage {
    Man_1, Man_2, Man_3,
    Man_4, Man_5, Man_6,;

    public static Avatar getRandomAvatar() {
        return Avatar.values()[(int) (Math.random()*Avatar.values().length)];
    }

    public static Array<String> getAllAvatarNames() {
        Array<String> names = new Array<>();
        for (Avatar avatar : Avatar.values()) {
            names.add(avatar.name());
        }
        return names;
    }

    public static Avatar getAvatarByName(String name) {
        for (Avatar avatar : Avatar.values()) {
            if (avatar.name().equals(name)) {
                return avatar;
            }
        }
        return null;
    }

    @Override
    public String getAddress() {
        return ("data/avatars/" + (ordinal() + 1) + ".png");
    }


}

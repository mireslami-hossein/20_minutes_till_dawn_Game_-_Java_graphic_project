package untilDown.com.Models.avatar;

public class AvatarCustom implements AvatarImage {
    String name;

    public AvatarCustom(String name) {
        this.name = name;
    }

    @Override
    public String getAddress() {
        return "data/avatars/" + name;
    }
}

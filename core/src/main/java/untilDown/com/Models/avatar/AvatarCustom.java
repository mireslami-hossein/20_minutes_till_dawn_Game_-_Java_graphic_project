package untilDown.com.Models.avatar;

public class AvatarCustom implements AvatarImage {
    String path;

    public AvatarCustom() {}

    public AvatarCustom(String path) {
        this.path = path;
    }

    @Override
    public String getAddress() {
        return path;
    }
}

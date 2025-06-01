package untilDown.com.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import untilDown.com.Models.avatar.Avatar;
import untilDown.com.Models.avatar.AvatarCustom;
import untilDown.com.Models.avatar.AvatarImage;

public class User {
    private String username;
    private String password;

    private String answer;
    private UserType userType;

    private AvatarImage avatar;
    private int score = 0;

    public User(){};

    public User(String username, String password, String answer, boolean isRegistered) {
        this.username = username;
        this.password = password;
        this.answer = answer;

        this.userType = isRegistered ? UserType.registered : UserType.Guest;
        this.avatar = Avatar.getRandomAvatar();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isGuest() {
        return userType == UserType.Guest;
    }

    public FileHandle getAvatarAddress() {
        return Gdx.files.local(avatar.getAddress());
    }

    public int getScore() {
        return score;
    }
}

enum UserType {
    Guest, registered
}

package untilDown.com.Models;

public class User {
    private String username;
    private String password;

    private String answer;
    private UserType userType;

    private Avatar avatar;
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

    public String getAvatarAddress() {
        return avatar.getAddress();
    }

    public int getScore() {
        return score;
    }
}

enum UserType {
    Guest, registered
}

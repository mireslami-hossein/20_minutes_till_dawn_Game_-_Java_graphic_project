package untilDown.com.Models;

public class User {
    private String username;
    private String password;

    private String answer;
    private UserType userType;

    private Avatar avatar = Avatar.getRandomAvatar();

    public User(String username, String password, String answer, boolean isRegistered) {
        this.username = username;
        this.password = password;
        this.answer = answer;

        this.userType = isRegistered ? UserType.registered : UserType.Guest;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isGuest() {
        return userType == UserType.Guest;
    }
}

enum UserType {
    Guest, registered
}

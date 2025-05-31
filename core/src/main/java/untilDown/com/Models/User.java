package untilDown.com.Models;

public class User {
    private String username;
    private String password;

    private String answer;

    public User(String username, String password, String answer) {
        this.username = username;
        this.password = password;
        this.answer = answer;
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
}

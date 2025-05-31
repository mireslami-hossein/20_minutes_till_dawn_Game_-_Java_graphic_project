package untilDown.com.Models;

public enum SecurityQuestion {
    Father_name("What is your father's name?"),
    City_name("What city were you born in?"),
    School_name("What is the name of your first school");

    String question;

    SecurityQuestion(String question) {
        this.question = question;
    }
}

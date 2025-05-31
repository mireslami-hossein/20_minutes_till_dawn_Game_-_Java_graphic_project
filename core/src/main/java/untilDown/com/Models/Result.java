package untilDown.com.Models;

import java.lang.Record;

public class Result {
    public boolean success = false;
    public String message;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}

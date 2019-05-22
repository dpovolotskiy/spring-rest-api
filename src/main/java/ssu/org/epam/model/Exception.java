package ssu.org.epam.model;

public class Exception {

    private String message;
    private long time;

    public Exception() {
    }

    public Exception(String message, long time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

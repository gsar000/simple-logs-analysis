package entities;

public class GroupParams {
    private String username;
    private TimeUnit timeUnit;

    public GroupParams(String username, TimeUnit timeUnit) {
        this.username = username;
        this.timeUnit = timeUnit;
    }

    public String getUsername() {
        return username;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

}

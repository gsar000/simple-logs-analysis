package entities;

import java.util.Date;

public class FilterParams {
    private String username;
    private Date startDate;
    private Date endDate;
    private String messagePattern;

    public FilterParams(String username, Date startDate, Date endDate, String messagePattern) {
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
        this.messagePattern = messagePattern;
    }

    public String getUsername() {
        return username;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getMessagePattern() {
        return messagePattern;
    }
}

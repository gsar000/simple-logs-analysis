package entities;

import java.util.Date;

public class LogRecord {
    private Date date;
    private String name;
    private String message;

    public LogRecord(Date date, String name, String message) {
        this.date = date;
        this.name = name;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}

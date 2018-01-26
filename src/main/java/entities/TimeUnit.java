package entities;

public enum TimeUnit {
    HOURS("Hours", "yyyy-MM-dd HH"),
    DAYS("Days", "yyyy-MM-dd"),
    MONTHS("Months", "yyyy-MM");

    private String type;
    private String format;


    TimeUnit(String type, String format) {
        this.type = type;
        this.format = format;
    }

    public String getType() {
        return type;
    }

    public String getFormat() {
        return format;
    }
}

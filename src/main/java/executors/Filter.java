package executors;

import entities.FilterParams;
import entities.LogRecord;

import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter {

    public static LinkedList<LogRecord> getFilteredRecords(LinkedList<LogRecord> logRecords, FilterParams filterParams) {
        LinkedList<LogRecord> filteredRecords = new LinkedList<>();

        for (LogRecord logRecord : logRecords) {

            boolean userNameIsEqual = filterParams.getUsername() == null || "".equals(filterParams.getUsername())
                    || Filter.userNameEquals(logRecord.getName(), filterParams.getUsername());

            boolean messageMatchesPattern = filterParams.getMessagePattern() == null
                    || "".equals(filterParams.getMessagePattern())
                    || Filter.messageMatches(logRecord.getMessage(), filterParams.getMessagePattern());

            boolean dateInThePeriod = Filter.dateIsInThePeriod(logRecord.getDate(),
                    filterParams.getStartDate(), filterParams.getEndDate());

            if (userNameIsEqual && dateInThePeriod && messageMatchesPattern) {
                filteredRecords.add(logRecord);
            }
        }
        return filteredRecords;
    }

    private static boolean userNameEquals(String logRecordUserMame, String filterUserName) {
        return logRecordUserMame.equals(filterUserName);
    }

    private static boolean dateIsInThePeriod(Date logRecordDate, Date start, Date end) {
        return (start == null || start.before(logRecordDate) || start.equals(logRecordDate)) &&
                (end == null || end.after(logRecordDate) || end.equals(logRecordDate));
    }

    private static boolean messageMatches(String logRecordMessage, String filterPattern) {
        Pattern pattern = Pattern.compile(filterPattern);
        Matcher matcher = pattern.matcher(logRecordMessage);
        return matcher.find();
    }
}

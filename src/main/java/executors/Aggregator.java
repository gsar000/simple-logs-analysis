package executors;

import entities.LogRecord;
import entities.TimeUnit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Aggregator {

    public static Integer getAggregatedByUserName(LinkedList<LogRecord> logRecords, String userName) {
        Integer count = 0;

        for (LogRecord logRecord : logRecords) {
            if (userName.equals(logRecord.getName())) {
                count++;
            }
        }

        return count;
    }

    public static TreeMap<String, Integer> getAggregatedByTime(LinkedList<LogRecord> logRecords, TimeUnit timeUnit) {
        TreeMap<String, Integer> aggregationResult = new TreeMap<String, Integer>();

        for (LogRecord logRecord : logRecords) {
            DateFormat df = new SimpleDateFormat(timeUnit.getFormat());
            String agrDate = df.format(logRecord.getDate());

            if (aggregationResult.containsKey(agrDate)) {
                aggregationResult.put(agrDate, aggregationResult.get(agrDate) + 1);
            } else {
                aggregationResult.put(agrDate, 1);
            }
        }

        return aggregationResult;
    }

}

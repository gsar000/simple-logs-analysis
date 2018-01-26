import entities.FilterParams;
import entities.GroupParams;
import entities.LogRecord;
import executors.*;

import java.io.File;
import java.util.LinkedList;
import java.util.TreeMap;

public class Runner {

    public static void main(String[] args) {
        File[] files = new File("./src/main/resources/logs").listFiles();

        if (files == null || files.length == 0) {
            System.out.println("Files with logs weren't found");
            System.exit(0);
        }

        LinkedList<LogRecord> logRecords = new LinkedList<>();

        FilterParams filterParams = Reader.readFilterParams();
        GroupParams groupParams = Reader.readGroupParams();
        String outputFilePath = Reader.readOutputFilePath();

        for (File file : files) {
            logRecords.addAll(Parser.getLogsFromFile(file));
        }


        LinkedList<LogRecord> filteredRecords = Filter.getFilteredRecords(logRecords, filterParams);

        if (groupParams.getTimeUnit() != null) {
            TreeMap<String, Integer> groupedByTime = Aggregator.getAggregatedByTime(filteredRecords, groupParams.getTimeUnit());
            Printer.printGroupedByTime(groupedByTime, groupParams.getTimeUnit());
        }

        if (!"".equals(groupParams.getUsername().trim())) {
            Integer groupedByUsername = Aggregator.getAggregatedByUserName(filteredRecords, groupParams.getUsername().trim());
            Printer.printGroupedByUsername(groupedByUsername, groupParams.getUsername());
        }

        Writer.writeFilteredRecordsToFile(filteredRecords, outputFilePath);
    }
}

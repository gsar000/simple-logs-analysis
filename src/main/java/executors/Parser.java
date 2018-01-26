package executors;

import entities.LogRecord;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {

    private static Logger logger = Logger.getLogger(Parser.class.getName());

    public static LinkedList<LogRecord> getLogsFromFile(File file) {
        LinkedList<LogRecord> logRecords = new LinkedList<LogRecord>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] parsedLine = line.split(";USERNAME:|;MESSAGE:");
                if (parsedLine.length > 2) {
                    logRecords.add(new LogRecord(Parser.getParsedDate(parsedLine[0]), parsedLine[1], parsedLine[2]));
                } else {
                    logger.log(Level.SEVERE, "Log record is not full in line: " + line + "; File: " + file.getName());
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, "File not found exception: ", ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "IO exception: ", ex);
        }
        return logRecords;
    }

    private static Date getParsedDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);

        } catch (ParseException ex) {
            return null;
        }
    }
}

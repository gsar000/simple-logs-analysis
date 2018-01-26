package executors;

import entities.LogRecord;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Writer {
    private static Logger logger = Logger.getLogger(Parser.class.getName());

    public static void writeFilteredRecordsToFile(LinkedList<LogRecord> logRecords, String filePath) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));) {

            for (LogRecord logRecord : logRecords) {
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(logRecord.getDate());
                String record = date + ";USERNAME:" + logRecord.getName() + ";MESSAGE:" + logRecord.getMessage();
                bw.write(record);
                bw.newLine();
            }

        } catch (IOException ex) {
            logger.log(Level.SEVERE, "IO exception: ", ex);
        }

    }

}

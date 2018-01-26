package executors;

import entities.FilterParams;
import entities.GroupParams;
import entities.TimeUnit;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reader {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static FilterParams readFilterParams() {
        String username;
        Date startDate;
        Date endDate;
        String messagePattern;

        while (true) {
            System.out.println("Please enter Username  for filtering and press Enter (you can leave it empty)");
            username = Reader.readString();

            System.out.println("Please enter Start date for filtering in next format 2017-25-11 18:30:15 " +
                    "and press Enter (you can leave it empty)");
            startDate = Reader.readDate();

            System.out.println("Please enter End date for filtering in next format 2017-25-11 18:30:15 " +
                    "and press Enter (you can leave it empty)");
            endDate = Reader.readDate();

            System.out.println("Please enter Message pattern for filtering and press Enter (you can leave it empty)");
            messagePattern = Reader.readString();

            if ("".equals(username) && "".equals(messagePattern) && startDate == null && endDate == null) {
                System.out.println("Sorry, at least on filtering parameter should be specified");
            } else {
                break;
            }

        }

        return new FilterParams(username, startDate, endDate, messagePattern);
    }

    public static GroupParams readGroupParams() {
        String username;
        TimeUnit timeUnit;

        while (true) {
            System.out.println("Please enter Username for grouping and press Enter (you can leave it empty)");
            username = Reader.readString();

            System.out.println("Please enter Time unit for grouping(hour, day or month) and press Enter (you can leave it empty)");
            timeUnit = Reader.readTimeUnit();

            if ("".equals(username) && timeUnit == null) {
                System.out.println("Sorry, at least one grouping parameter should be specified");
            } else {
                break;
            }

        }

        return new GroupParams(username, timeUnit);
    }

    public static String readOutputFilePath() {
        String pathToFile;
        System.out.println("Please enter output file path");
        while (true) {
            try {
                pathToFile = br.readLine();
                FileWriter fw = new FileWriter(pathToFile);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("tempLine");
                bw.close();
                break;
            } catch (IOException ex) {
                System.out.println("Sorry some problems: " + ex.getMessage() +
                        ". Check path to the file and access rules to it. Please try again:");
            }
        }

        return pathToFile;
    }

    private static String readString() {
        String readStr;
        while (true) {
            try {
                readStr = br.readLine();
                break;
            } catch (IOException ex) {
                System.out.println("Sorry some problems: " + ex.getMessage() + ". Please try again:");
            }
        }

        return readStr;
    }

    private static TimeUnit readTimeUnit() {
        String readStr;
        while (true) {
            try {
                readStr = br.readLine();
                switch (readStr) {
                    case "hour":
                        return TimeUnit.HOURS;
                    case "day":
                        return TimeUnit.DAYS;
                    case "month":
                        return TimeUnit.MONTHS;
                    case "":
                        return null;
                    default:
                        System.out.println("Sorry, time unit should be hour, day or month. Try again:");
                }
            } catch (IOException ex) {
                System.out.println("Sorry, some problems: " + ex.getMessage() + ". Please try again:");
            }
        }
    }

    private static Date readDate() {
        Date date;
        while (true) {

            String readDate;
            try {
                readDate = br.readLine();
                if (!"".equals(readDate)) {
                    date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(readDate);
                } else {
                    date = null;
                }
                break;
            } catch (IOException | ParseException ex) {
                System.out.println("Sorry some problems: " + ex.getMessage() +
                        ". Please check format of entered date and try again:");
            }
        }
        return date;
    }

}

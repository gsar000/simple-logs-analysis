package executors;

import entities.TimeUnit;

import java.util.TreeMap;

public class Printer {

    public static void printGroupedByTime(TreeMap<String, Integer> groupedRecords, TimeUnit timeUnit) {
        System.out.println("-----------------------------");
        System.out.println("Grouped by time(" + timeUnit.getType() + ")");
        System.out.println("------------------------------");
        System.out.println(timeUnit.getType() + ":   |   Count:    ");
        for (String key : groupedRecords.keySet()) {
            System.out.println(key + " | " + groupedRecords.get(key));
        }
    }

    public static void printGroupedByUsername(Integer count, String userName) {
        System.out.println("-----------------------------");
        System.out.println("Grouped by username(" + userName + ")");
        System.out.println("------------------------------");
        System.out.println("Username:   |   Count:    ");
        System.out.println(userName + " | " + count);
    }
}

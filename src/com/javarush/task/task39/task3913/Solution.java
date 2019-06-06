package com.javarush.task.task39.task3913;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
//        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        LogParser logParser = new LogParser(Paths.get("C:\\JavaRush\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39\\task3913\\logs"));
//        checkIPQuery(logParser);
//        checkUserQuery(logParser);
//        checkDateQuery(logParser);
//        checkEventQuery(logParser);
        checkQLQuery(logParser);
    }

    private static void checkIPQuery(LogParser logParser) {
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getIPsForUser("Amigo", null, null));
        System.out.println(logParser.getIPsForEvent(Event.LOGIN, null, null));
        System.out.println(logParser.getIPsForStatus(Status.OK, null, new Date()));
    }

    private static void checkUserQuery(LogParser logParser) {
        System.out.println("getAllUsers" + logParser.getAllUsers());
        System.out.println("getNumberOfUsers all from now to infinity " + logParser.getNumberOfUsers(new Date(), null));
        System.out.println("getNumberOfUserEvents for Amigo from now to infinity" + logParser.getNumberOfUserEvents("Amigo", new Date(), null));
        System.out.println("getUsersForIP for 120.120.120.122 for all date" + logParser.getUsersForIP("120.120.120.122", null, null));
        System.out.println("getLoggedUsers" + logParser.getLoggedUsers(null, null));
        System.out.println("getDownloadedPluginUsers" + logParser.getDownloadedPluginUsers(null, null));
        System.out.println("getWroteMessageUsers" + logParser.getWroteMessageUsers(null, null));
        System.out.println("getSolvedTaskUsers" + logParser.getSolvedTaskUsers(null, null));
        System.out.println("getSolvedTaskUsers task 15" + logParser.getSolvedTaskUsers(null, null, 15));
    }

    private static void checkDateQuery(LogParser logParser) {
        System.out.println("1 getDatesForUserAndEvent for Eduard wrote messages: " + logParser.getDatesForUserAndEvent("Eduard Petrovich Morozko", Event.WRITE_MESSAGE, null, null));
        System.out.println("2 getDatesWhenSomethingFailed: " + logParser.getDatesWhenSomethingFailed(null, null));
        System.out.println("3 getDatesWhenErrorHappened: " + logParser.getDatesWhenErrorHappened(null, null));
        System.out.println("4 getDateWhenUserLoggedFirstTime for Eduard: " + logParser.getDateWhenUserLoggedFirstTime("Eduard Petrovich Morozko", null, null));
        System.out.println("5 getDateWhenUserSolvedTask for 1 task by Vasya: " + logParser.getDateWhenUserSolvedTask("Vasya Pupkin", 1, null, null));
        System.out.println("6 getDateWhenUserDoneTask for task48 by Eduard" + logParser.getDateWhenUserDoneTask("Eduard Petrovich Morozko", 48, null, null));
        System.out.println("7 getDatesWhenUserWroteMessage for Eduard" + logParser.getDatesWhenUserWroteMessage("Eduard Petrovich Morozko", null, null));
        System.out.println("8 getDatesWhenUserDownloadedPlugin for Eduard" + logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko", null, null));
    }

    private static void checkEventQuery(LogParser logParser) {
        System.out.println("for task 2 succesful attemt is: " + logParser.getNumberOfSuccessfulAttemptToSolveTask(2, null, null));
    }

    private static void checkQLQuery(LogParser logParser) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String query;
            System.out.println("введите запрос:");
            while (!(query = reader.readLine()).equals("exit")) {
                System.out.println(logParser.execute(query));
                System.out.println("введите запрос:");
            }
        } catch (Exception e) {
            System.out.println("some Exception with writing query");
            System.exit(-1);
        }
    }
}
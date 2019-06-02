package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery {
    private Path logDir;//директория с логами *.log
    private List<MyLog> logs = new ArrayList<>();

    public LogParser(Path logDir) {
        this.logDir = logDir;
        File[] files = logDir.toFile().listFiles();
        if (files != null) {
            for (File file : files) {
                if(file.getName().endsWith(".log")) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String log;
                        while ((log = reader.readLine()) != null) {
                            parseString(log);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void parseString(String log) {
        String[] logParts = log.split("\t");
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        try {
            String ip = logParts[0];
            String user = logParts[1];
            Date date = df.parse(logParts[2]);
            Event event = Event.valueOf(logParts[3].split(" ")[0]);
            int taskNumber = 0;
            if (event == Event.SOLVE_TASK ||
                    event == Event.DONE_TASK)
                taskNumber = Integer.parseInt(logParts[3].split(" ")[1]);
            Status status = Status.valueOf(logParts[4]);
            logs.add(new MyLog(ip, user, date, event, taskNumber, status));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDates(after, before, log.date))
                set.add(log.ipString);
        }
        return set;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (MyLog log : logs) {
            if (log.user.equals(user) && checkDates(after, before, log.date))
                set.add(log.ipString);
        }
        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (MyLog log : logs) {
            if (log.event == event && checkDates(after, before, log.date))
                set.add(log.ipString);
        }
        return set;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (MyLog log : logs) {
            if (log.status == status && checkDates(after, before, log.date))
                set.add(log.ipString);
        }
        return set;
    }


    private boolean checkDates(Date after, Date before, Date current) {
        if (after == null)
            after = new Date(0);
        if (before == null)
            before = new Date(Long.MAX_VALUE);
        if (current.getTime() > after.getTime() && current.getTime() < before.getTime())
            return true;
        return false;
    }

    private class MyLog {
        private int[] ip = new int[4];
        private String ipString;
        private String user;
        private Date date;
        private Event event;
        private int taskNumber;
        private Status status;

        public MyLog(String ipString, String user, Date date, Event event, int taskNumber, Status status) {
            ipParse(ipString);
            this.user = user;
            this.date = date;
            this.event = event;
            this.taskNumber = taskNumber;
            this.status = status;
            this.ipString = ipString;
        }
        private void ipParse(String ipString) {
            String[] ipParts = ipString.split("\\.");
            for (int i = 0; i < 4; i++) {
                ip[i] = Integer.parseInt(ipParts[i]);
            }
        }
    }
}
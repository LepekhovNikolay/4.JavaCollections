package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.EventQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery {
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

    //Start implementation of EventQuery

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before,log.date))
                set.add(log.event);
        }
        return set;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before,log.date) &&
                    log.ip.equals(ip))
                set.add(log.event);
        }
        return set;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before,log.date) &&
                    log.user.equals(user))
                set.add(log.event);
        }
        return set;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return getEventsWithStatus(after, before, Status.FAILED);
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return getEventsWithStatus(after, before, Status.ERROR);
    }

    private Set<Event> getEventsWithStatus(Date after, Date before, Status status) {
        Set<Event> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before,log.date) &&
                    log.status == status)
                set.add(log.event);
        }
        return set;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int result = 0;
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date) &&
                    log.event == Event.SOLVE_TASK &&
                    log.task == task)
                result++;
        }
        return result;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int result = 0;
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date) &&
                    log.event == Event.DONE_TASK &&
                    log.task == task /*&&
                    log.status == Status.OK*/)
                result++;
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return getAllAttempts(after, before, Event.SOLVE_TASK);
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return getAllAttempts(after, before, Event.DONE_TASK);
    }

    private Map<Integer, Integer> getAllAttempts(Date after, Date before, Event event) {
        Map<Integer, Integer> map = new HashMap<>();
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date) &&
                    log.event == event) {
                Integer attempt = map.get(log.task);
                if (attempt == null)
                    attempt = 1;
                else
                    attempt++;
                map.put(log.task, attempt);
            }
        }
        return map;
    }


    //Start implementation of DateQuery

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date) &&
                    log.user.equals(user) &&
                    log.event == event)
                set.add(log.date);
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getDatesWithStatus(after, before, Status.FAILED);
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getDatesWithStatus(after, before, Status.ERROR);
    }

    private Set<Date> getDatesWithStatus(Date after, Date before, Status status) {
        Set<Date> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date) &&
                    log.status == status)
                set.add(log.date);
        }
        return set;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Set<Date> set = getDatesForUserAndEvent(user, Event.LOGIN, after, before);
        if (set.size() == 0) {
            return null;
        } else {
            Date result = new Date(Long.MAX_VALUE);
            for (Date date : set) {
                if (date.getTime() < result.getTime()) {
                    result = date;
                }
            }
            return result;
        }
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        return getFirstDateTask(user, task, after, before, Event.SOLVE_TASK);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        return getFirstDateTask(user, task, after, before, Event.DONE_TASK);
    }

    private Date getFirstDateTask(String user, int task, Date after, Date before, Event event) {
        Date date = null;
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date) &&
                    log.user.equals(user) &&
                    log.event == event &&
                    log.task == task) {
                if (date == null)
                    date = log.date;
                else if (log.date.getTime() < date.getTime()){
                    date = log.date;
                }
            }
        }
        return date;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getDatesWithEvent(user, after, before, Event.WRITE_MESSAGE);
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getDatesWithEvent(user, after, before, Event.DOWNLOAD_PLUGIN);
    }

    private Set<Date> getDatesWithEvent(String user, Date after, Date before, Event event) {
        Set<Date> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date) &&
                    log.user.equals(user) &&
                    log.event == event)
                set.add(log.date);
        }
        return set;
    }

    //Start implementation of UserQuery
    @Override
    public Set<String> getAllUsers() {
        Set<String> set = new HashSet<>();
        for (MyLog log : logs) {
            set.add(log.user);
        }
        return set;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date))
                set.add(log.user);
        }
        return set.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date) &&
                    log.user.equals(user))
                set.add(log.event);
        }
        return set.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date) &&
                    log.ip.equals(ip))
                set.add(log.user);
        }
        return set;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getUsersByEvent(after, before, Event.LOGIN);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getUsersByEvent(after, before, Event.DOWNLOAD_PLUGIN);
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getUsersByEvent(after, before, Event.WRITE_MESSAGE);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getUsersByEvent(after, before, Event.SOLVE_TASK);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getUsersByEvent(after, before, Event.SOLVE_TASK, task);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getUsersByEvent(after, before, Event.DONE_TASK);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getUsersByEvent(after, before, Event.DONE_TASK, task);
    }

    private Set<String> getUsersByEvent(Date after, Date before, Event event) {
        Set<String> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date) &&
                    log.event == event/* &&
                    log.status == Status.OK*/)
                set.add(log.user);
        }
        return set;
    }

    private Set<String> getUsersByEvent(Date after, Date before, Event event, int task) {
        Set<String> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date) &&
                    log.event == event &&
                    log.task == task /*&&
                    log.status == Status.OK*/)
                set.add(log.user);
        }
        return set;
    }

    //Start implementation IPQuery
    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date))
                set.add(log.ip);
        }
        return set;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date) &&
                    log.user.equals(user))
                set.add(log.ip);
        }
        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date) &&
                    log.event == event)
                set.add(log.ip);
        }
        return set;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (MyLog log : logs) {
            if (checkDate(after, before, log.date) &&
                    log.status == status)
                set.add(log.ip);
        }
        return set;
    }


    private boolean checkDate(Date after, Date before, Date current) {
        if (after == null)
            after = new Date(0);
        if (before == null)
            before = new Date(Long.MAX_VALUE);
        if (after.getTime() < current.getTime() &&
                before.getTime() > current.getTime())
            return true;
        return false;
    }

    private class MyLog {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private int task;
        private Status status;

        public MyLog(String ipString, String user, Date date, Event event, int taskNumber, Status status) {
            this.user = user;
            this.date = date;
            this.event = event;
            this.task = taskNumber;
            this.status = status;
            this.ip = ipString;
        }
    }
}
package com.javarush.task.task39.task3911;

import java.util.*;

public class Software {
    int currentVersion;

    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    public boolean rollback(int rollbackVersion) {
        if (rollbackVersion > versionHistoryMap.size() ||  rollbackVersion < 1)
            return false;
        for (int i = versionHistoryMap.size() - 1; i > 0; i--) {
            versionHistoryMap.remove(i+1);
            if (i == rollbackVersion) {
                currentVersion = rollbackVersion;
                return true;
            }
        }
        return false;
    }
}

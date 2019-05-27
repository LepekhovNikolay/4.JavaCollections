package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy{
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT = 10_000;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private long maxBucketSize;

    public FileStorageStrategy() {
        for (int i = 0; i < DEFAULT_INITIAL_CAPACITY;i++)
            table[i] = new FileBucket();
    }

    public int hash(Long k) {
        return k.hashCode();
    }

    public int indexFor(int hash, int length) {
        return hash & length -1;
    }

    public Entry getEntry(Long key) {
        if (size == 0)
            return null;
        int hash = (key == null) ? 0 : hash(key);
        for (Entry e = table[indexFor(hash, table.length)].getEntry(); e != null; e = e.next) {
            if (e.hash == hash && e.key.equals(key)  || key != null && key.equals(e.key))
                return e;
        }
        return null;
    }

    public void resize(int newCapacity) {
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    private void transfer(FileBucket[] newTable) {
        for (FileBucket fileBucket : table) {
            if (fileBucket == null) continue;
            Entry entry = fileBucket.getEntry();
            while (entry != null) {
                Entry next = entry.next;
                int newIndex = indexFor(entry.hash, newTable.length);
                if (newTable[newIndex] == null) {
                    entry.next = null;
                    newTable[newIndex] = new FileBucket();
                } else
                    entry.next = newTable[newIndex].getEntry();
                newTable[newIndex].putEntry(entry);
                entry = next;
            }
            fileBucket.remove();
        }
    }

    private void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, entry));
        size++;
        if (table[bucketIndex].getFileSize() > bucketSizeLimit) resize(2 * table.length);
    }
    private void createEntry(int hash, Long key, String value, int bucketIndex) {
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        for (FileBucket fileBucket : table) {
            if (fileBucket == null) continue;
            Entry entry = fileBucket.getEntry();
            while (entry != null) {
                if (entry.getValue().equals(value)) return true;
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int index = indexFor(hash(key), table.length);
        if (table[index] != null) {
            Entry entry = table[index].getEntry();
            while (entry != null) {
                if (entry.getKey().equals(key)) {
                    entry.value = value;
                    return;
                }
                addEntry(hash(key), key, value, index);
            }
        } else {
            createEntry(hash(key), key, value, index);
        }
    }

    @Override
    public Long getKey(String value) {
        for (FileBucket fileBucket : table) {
            if (fileBucket == null) continue;
            Entry entry = fileBucket.getEntry();
            while (entry != null) {
                if (entry.value.equals(value)) return  entry.key;
                entry = entry.next;
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        Entry entry = getEntry(key);
        if (entry != null) return entry.value;
        return null;
    }
}

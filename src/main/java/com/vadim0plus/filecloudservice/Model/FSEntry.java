package com.vadim0plus.filecloudservice.Model;

public abstract class FSEntry {
    // Entry is superclass for both File and Directory
    //protected Directory parent;
    protected long created;
    protected long lastUpdated;
    protected long lastAccessed;
    protected String name;

    public FSEntry(String n) {
        name = n;
        //parent = p;
        created = System.currentTimeMillis();
        lastUpdated = System.currentTimeMillis();
        lastAccessed = System.currentTimeMillis();
    }

    public abstract int size();

    /* Getters and setters. */
    public long getcreationTime() {
        return created;
    }

    public long getLastUpdatedTime() {
        return lastUpdated;
    }

    public long getLastAccessedTime() {
        return lastAccessed;
    }

    public void changeName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }
}
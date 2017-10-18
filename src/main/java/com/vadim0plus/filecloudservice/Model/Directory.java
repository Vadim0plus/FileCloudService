package com.vadim0plus.filecloudservice.Model;

import java.util.ArrayList;
import java.util.Iterator;

public class Directory extends FSEntry {
    protected ArrayList<FSEntry> contents;

    public Directory(String n)
    {
        super(n);
        contents = new ArrayList<FSEntry>();
    }
    public int size()
    {
        int size = 0;
        for (FSEntry e : contents)
            size += e.size();

        return size;
    }
    public int numberOfFiles()
    {
        int count = 0;
        for (FSEntry e : contents)
        {
            if (e instanceof Directory)
            {
                count++; // Directory counts as a file
                Directory d = (Directory) e;
                count += d. numberOfFiles ();
            }
            else if (e instanceof File)
                count++;
        }
        return count;
    }

    public boolean deleteEntry(String name)
    {
        Iterator<FSEntry> iter = contents.iterator();
        while(iter.hasNext()) {
            FSEntry entry = iter.next();
            if(entry.getName().equals(name)){
                iter.remove();
                return true;
            }
        }
        return false;
    }

    public boolean addEntry(FSEntry entry)
    {
        return contents.add(entry);
    }

    /*public void addFile(String name, int size) {
        File file = new File(name,this,size);
        contents.add(file);
    }*/

    /*
    public boolean addDir(String name, String path) {
        FSEntry entry = getEntry(path);
        if(entry instanceof Directory) {
            Directory d = (Directory) entry;
            d.addDir(name);
            return true;
        }
        return false;
    }*/

    /*
    public void addDir(String name) {
        Directory dir = new Directory(name,this);
        contents.add(dir);
    }*/

    public ArrayList<FSEntry> getContents()
    {
        return contents;
    }

    /*
    protected String print(String conc) {
        StringBuffer buf = new StringBuffer();
        ArrayList<FSEntry> contents = getContents();
        for (FSEntry e : contents)
        {
            if (e instanceof Directory)
            {
                Directory d = (Directory) e;
                String subs = d.print(conc+getName()+"/");
                buf.append(subs);
            }
            else if (e instanceof File) {
                File f = (File) e;
                buf.append(conc+getName()+"/"+f.getName()+"\n");
            }
        }
        return buf.toString();
    }*/

    /*
    @Override
    public String toString() {
        return print("");
    }*/

    public FSEntry getEntry(String fn) {
        for(FSEntry entry : contents) {
            if(entry.getName().equals(fn)) {
                return entry;
            }
        }
        return null;
    }

    /*
    public FSEntry getEntry(String fn, String path) {
        Directory d = this;
        String curPath = path;
        while(true) {
            if (!curPath.contains("/"))
                return d.getEntry(fn);
            else {
                int i = 0;
                while (curPath.charAt(i) != '/') {
                    i++;
                    if (i == curPath.length() - 1)
                        break;
                }
                curPath = curPath.substring(0, i - 1);
                FSEntry entry = d.getEntry(curPath);
                if (entry != null && entry instanceof Directory) {
                    d = (Directory) entry;
                } else {
                    return null;
                }
            }
        }
        */
}
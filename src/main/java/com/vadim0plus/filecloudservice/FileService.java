package com.vadim0plus.filecloudservice;

import com.vadim0plus.filecloudservice.Model.Directory;
import com.vadim0plus.filecloudservice.Model.FSEntry;

import java.util.ArrayList;
import java.util.Arrays;

public class FileService {
    private Directory cd;

    public FileService() {
        cd = new Directory("/");
    }

    public FileService(Directory dir) {
        cd = dir;
    }

    public void setCd(Directory cd) {
        this.cd = cd;
    }

    public Directory getCd() {
        return cd;
    }

    public boolean mkdir(String fn, boolean recur) {
        if (!fn.contains("/"))
            return cd.addEntry(new Directory(fn));
        else if(recur == false){
            int lastIndex = fn.lastIndexOf('/');
            Directory d = getDir(fn.substring(0, lastIndex));
            if(d == null)
                return false;
            return d.addEntry(new Directory(fn.substring(lastIndex + 1)));
        }else {
            Directory d = cd;
            FSEntry entry;
            String[] entries = fn.split("/");
            for (int i = 0; i < entries.length; i++) {
                if(!d.addEntry(new Directory(entries[i])))
                    return false;
                d =(Directory) d.getEntry(entries[i]);
            }
            return true;
        }
    }

    public Directory getDir(String path) {
        Directory d = cd;
        FSEntry entry;
        String[] entries = path.split("/");
        for (int i = 0; i < entries.length; i++) {
            entry = d.getEntry(entries[i]);
            if (entry != null && entry instanceof Directory) {
                d = (Directory) entry;
            } else
                return null;
        }
        return d;
    }

    public FSEntry getEntry(String fn) {
            if (!fn.contains("/"))
                return cd.getEntry(fn);
            else {
                int lastIndex = fn.lastIndexOf('/');
                Directory d = getDir(fn.substring(0, lastIndex));
                return d.getEntry(fn.substring(lastIndex + 1));
            }
    }

    public boolean remove(String fn) {
        if (!fn.contains("/"))
            return cd.deleteEntry(fn);
        else {
            int lastIndex = fn.lastIndexOf('/');
            Directory d = getDir(fn.substring(0,lastIndex));
            return d.deleteEntry(fn.substring(lastIndex+1));
        }
    }

    public boolean move(String src, String destDir) {
        FSEntry srcEntry;
        Directory srcDir = cd;
        Directory destDirEntry = cd;
        if (!src.contains("/"))
            srcEntry = cd.getEntry(src);
        else {
            int lastIndex = src.lastIndexOf('/');
            srcDir = getDir(src.substring(0, lastIndex));
            if(srcDir == null)
                return false;
            srcEntry = srcDir.getEntry(src.substring(lastIndex + 1));
        }
        if(srcEntry == null)
            return false;
        destDirEntry = getDir(destDir);
        if(destDirEntry == null)
            return false;
        if(!destDirEntry.addEntry(srcEntry))
            return false;
        if(!srcDir.deleteEntry(srcEntry.getName()))
            return false;
        return true;
    }

    public boolean copy(String src, String destDir) {
        FSEntry srcEntry;
        Directory srcDir = cd;
        Directory destDirEntry = cd;
        if (!src.contains("/"))
            srcEntry = cd.getEntry(src);
        else {
            int lastIndex = src.lastIndexOf('/');
            srcDir = getDir(src.substring(0, lastIndex));
            if(srcDir == null)
                return false;
            srcEntry = srcDir.getEntry(src.substring(lastIndex + 1));
        }
        if(srcEntry == null)
            return false;
        destDirEntry = getDir(destDir);
        if(destDirEntry == null)
            return false;
        if(!destDirEntry.addEntry(srcEntry))
            return false;
        return true;
    }

    public String[] print(String fn, boolean recur) {
        ArrayList<String> tree = new ArrayList<>();
        Directory d = getDir(fn);
        if (d == null)
            return null;
        for (FSEntry entry : d.getContents()) {
            tree.add(entry.getName());
            if ((entry instanceof Directory) && recur) {
                String[] subTree = print(fn+"/" + entry.getName(), true);
                tree.addAll(Arrays.asList(subTree));
            }
        }
        return (String[]) tree.toArray();
    }


}

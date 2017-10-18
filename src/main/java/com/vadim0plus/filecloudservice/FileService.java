package com.vadim0plus.filecloudservice;

import com.vadim0plus.filecloudservice.Model.Directory;
import com.vadim0plus.filecloudservice.Model.FSEntry;

public class FileService {
    private Directory root;

    public boolean mkdir(String name, String path) {
        return root.addDir(name, path);
    }

    public boolean remove(String name) {
        FSEntry entry = null;
        if(name.contains("/")) {
            entry = root.getEntry(
                    name.substring(name.lastIndexOf("/")),
                    name.substring(0, name.lastIndexOf("/",name.length()-1))
                    );
        } else {
            entry = root.getEntry(name);
        }
        if(entry != null ){
            entry.delete();
            return true;
        }
        return false;
    }

    public boolean move(String src, String dir) {
        FSEntry entry = null;
        if(src.contains("/")) {
            entry = root.getEntry(
                    src.substring(src.lastIndexOf("/")),
                    src.substring(0, src.lastIndexOf("/",src.length()-1))
            );
            // don't implement
        }
    }


}

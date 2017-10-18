package com.vadim0plus.filecloudservice.DAO;

import com.vadim0plus.filecloudservice.Model.Directory;
import com.vadim0plus.filecloudservice.Model.File;
import junit.framework.Assert;
import org.junit.Test;

public class FSEntryTest {
    @Test
    public void test() {
        String expectedTree = "root/file1\n"+
                              "root/file2\n"+
                              "root/file3\n";
        Directory root = new Directory("root",null);
        root.addFile("file1",1);
        root.addFile("file2",2);
        root.addFile("file3",3);
        String tree = root.toString();
        Assert.assertEquals(expectedTree,tree);
    }

    @Test
    public void test2() {
        String expectedTree = "root/file1\n"+
                "root/file2\n"+
                "root/file3\n";
        Directory root = new Directory("root",null);
        root.addFile("file1",1);
        root.addDir("dir1");
        root.
        root.addFile("file2",2);
        root.addFile("file3",3);
        String tree = root.toString();
        Assert.assertEquals(expectedTree,tree);
    }
}

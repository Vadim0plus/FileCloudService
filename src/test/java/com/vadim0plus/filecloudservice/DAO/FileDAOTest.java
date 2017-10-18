package com.vadim0plus.filecloudservice.DAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileDAOTest {
    final private String source = "Big big big text";
    private byte buf[];
    final private String fileName = "test.txt";
    FileDAO fileDAO = new FileDAO();
    @Before
    public void setUp() throws Exception {
        buf = source.getBytes();
    }

    @After
    public void tearDown() throws Exception {
        File file = new File(fileName);
        file.delete();

    }

    @Test
    public void readFile() throws Exception {
        try (FileOutputStream fOut = new FileOutputStream(fileName)) {
            fOut.write(buf);
            byte resultBuf[] = fileDAO.readFile(fileName);
            Assert.assertArrayEquals(buf,resultBuf);
        } catch(IOException e) {
            fail("IOException while test");
        }
    }

    @Test
    public void writeFile() throws Exception {
        fileDAO.writeFile(fileName,buf);
        File file = new File(fileName);
        try (FileInputStream fin = new FileInputStream(file)) {
            byte resultBuf[] = new byte[(int)file.length()];
            fin.read(resultBuf);
            Assert.assertArrayEquals(buf,resultBuf);
        } catch(IOException e) {
            fail("IOException while test");
        }
    }

    @Test
    public void copyFile() throws Exception {
        final String dest = "newtest.txt";
        try (FileOutputStream fOut = new FileOutputStream(fileName)) {
            fOut.write(buf);
        } catch(IOException e) {
            fail("IOException while test");
        }
        fileDAO.copyFile(fileName,dest);
        File file = new File(dest);
        try (FileInputStream fin = new FileInputStream(file)) {
            byte resultBuf[] = new byte[(int)file.length()];
            fin.read(resultBuf);
            Assert.assertArrayEquals(buf,resultBuf);
        } catch(IOException e) {
            fail("IOException while test");
        }

    }

}
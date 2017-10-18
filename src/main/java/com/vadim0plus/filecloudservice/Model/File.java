package com.vadim0plus.filecloudservice.Model;

public class File extends FSEntry {
    private String content;
    private int size;

    public File(String n)
    {
        super(n);
        size = 0;
        content ="";
    }
    public int size()
    {
        return size;
    }
    public String getContents()
    {
        return content;
    }
    public void setContents(String c)
    {
        content = c;
        size = c.length();
    }
}

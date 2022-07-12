package com.example.project1;

import java.io.Serializable;

public class MyFile implements Serializable {

    private int id;
    private String name;
    private byte[] deta;
    private String fileExtention;
    private int fileLength;
    private String channalName;
    private String guestName;
    private String auther;

    public MyFile(String name,int fileLength, byte[] data, String auther) {
       this.name = name;
       this.fileLength = fileLength;
       this.deta = data;
       this.auther = auther;
    }

    public void setChannalName(String channalName) {
        this.channalName = channalName;
    }

    public String getChannalName() {
        return channalName;
    }

    public String getAuther() {
        return auther;
    }

    public void setGues(String gues) {
        this.guestName = gues;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getDeta() {
        return deta;
    }

    public String getFileExtention() {
        return fileExtention;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeta(byte[] deta) {
        this.deta = deta;
    }

    public void setFileExtention(String fileExtention) {
        this.fileExtention = fileExtention;
    }
}

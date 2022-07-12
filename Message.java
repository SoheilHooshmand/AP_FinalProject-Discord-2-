package com.example.project1;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Message implements Serializable {

    private String text;
    private String auther;
    private String groupName;
    private String host;
    private String guess;
    private LocalDateTime localDateTime;
    private String access;
    private String showGroup;
    private String nameOfFile = "";
    private byte[] deta;
    private int lenghOfFile;
    private String channalName = "";

    public Message(String text, String auther, String access) {
        this.text = text;
        this.auther = auther;
        this.access = access;
        localDateTime = LocalDateTime.now();
    }

    public void setChannalName(String channalName) {this.channalName = channalName;}
    public void setLenghOfFile(int lenghOfFile) {this.lenghOfFile = lenghOfFile;}
    public void setNameOfFile(String nameOfFile) {this.nameOfFile = nameOfFile;}
    public void setDeta(byte[] date){this.deta = date;}
    public int getLenghOfFile(){return lenghOfFile;}
    public String getNameOfFile() {return nameOfFile;}
    public byte[] getDeta() {return deta;}
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String toString() {
        return auther + ": " + text + "(" + localDateTime + ")";
    }

    public String getGroupName() {
        return  groupName;
    }
    public String getChannalName() {return channalName;}

    public void setShowGroup(String showGroup) {this.showGroup = showGroup;}

    public String getHost() {
        return host;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getGuess() {
        return guess;
    }
    public String getAccess() {
        return access;
    }
    public String getAuther() {return auther;}
    public String getShowGroup() {return showGroup;}
}

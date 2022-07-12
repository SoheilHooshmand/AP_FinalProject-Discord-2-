package com.example.project1;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class AnswerGroupRequests {
    private Register register;
    private String groupName;
    @FXML
    private Button yes = new Button();
    @FXML
    private Button no = new Button();
    @FXML
    private Button back = new Button();
    private String showGroup;
    public void setRegister(Register register) {
        this.register = register;
    }
    public void setShowGroup(String showGroup) {this.showGroup = showGroup;}
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    @FXML
    public void yes() {
        int delete = 0;
        int chose = 0;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Group.fxml"));
            Parent root = loader.load();
            Group group = loader.getController();
            group.setNameOfGroup(groupName);
            group.addUSer(this.register);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userAbilities.fxml"));
            Parent root = fxmlLoader.load();
            UserAbilities userAbilities  = fxmlLoader.getController();
            userAbilities.recieveRegister(this.register);
            userAbilities.addGroup(this.groupName, showGroup);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(register.getUserName() + "JoinGroup.binay");
        File file1 = new File(register.getUserName() + "ShowGroupJoinGroup.binary");
        ArrayList<String > strings = new ArrayList<>();
        ArrayList<String> strings1 = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file1);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            strings1 = (ArrayList<String>) in.readObject();
            fileInputStream.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            strings = (ArrayList<String>) in.readObject();
            fileInputStream.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < strings.size(); i++) {
            if (strings.get(i).equals(groupName)) {
                delete = i;
                break;
            }
        }
        strings.remove(groupName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(strings);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        strings1.remove(delete);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(strings1);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void no() {
        int delete = 0;
        File file = new File(register.getUserName() + "JoinGroup.binay");
        File file1 = new File(register.getUserName() + "ShowGroupJoinGroup.binary");
        ArrayList<String> strings1 = new ArrayList<>();
        ArrayList<String > strings = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file1);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            strings1 = (ArrayList<String>) in.readObject();
            fileInputStream.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            strings = (ArrayList<String>) in.readObject();
            fileInputStream.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < strings.size(); i++) {
            if (strings.get(i).equals(groupName)) {
                delete = i;
            }
        }
        strings.remove(groupName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(strings);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        strings1.remove(delete);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(strings1);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void back(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showGroupRequestes.fxml"));
            Parent root = loader.load();
            ShowGroupRequestes showGroupRequestes = loader.getController();
            showGroupRequestes.receiveRegister(this.register);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

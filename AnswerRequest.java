package com.example.project1;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class AnswerRequest {
    @FXML
    private Button yes = new Button();
    @FXML
    private Button no = new Button();
    @FXML
    private Button back = new Button();
    @FXML
    private Label label = new Label();
    private Register register;
    private String nameOfFriend;

    public void setRegister(Register register) {this.register = register;}
    public void setNameOfFriend(String  nameOfFriend) {this.nameOfFriend = nameOfFriend;}
    @FXML
    public void yes() {

        Register accepted = null;
       // System.out.println("!@#" + nameOfFriend);
        ArrayList<Register> registers = new ArrayList<>();
        registers = this.register.getRegisters();
        for (Register register : registers) {
            if (register.getUserName().equals(this.nameOfFriend)) {
                accepted = register;
            }
        }
       // System.out.println("#$" + accepted.getUserName());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userAbilities.fxml"));
            Parent root = loader.load();
            UserAbilities userAbilities = loader.getController();
            userAbilities.recieveRegister(this.register);
            userAbilities.addFriend(accepted);
            System.out.println("& added &");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Register register2 = null;
        for (Register register : registers) {
            if (register.getUserName().equals(nameOfFriend)) {
                register2 = register;
                break;
            }
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userAbilities.fxml"));
            Parent root = loader.load();
            UserAbilities userAbilities = loader.getController();
            userAbilities.recieveRegister(register2);
            userAbilities.addFriend(this.register);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(register.getUserName() + "receiverRequest.binary");
        ArrayList<String> strings = new ArrayList<>();
        if (file.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
                strings = (ArrayList<String>) inputStream.readObject();
                fileInputStream.close();
                inputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            strings.remove(nameOfFriend);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(strings);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void no() {
        ArrayList<String> strings = new ArrayList<>();
        File file = new File(register.getUserName() + "receiverRequest.binary");
        if (file.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                strings = (ArrayList<String>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            strings.remove(nameOfFriend);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(strings);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void back (Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("friendHandling.fxml"));
            Parent root = loader.load();
            FriendsHandling friendsHandling = loader.getController();
            friendsHandling.receiveRegister(this.register);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

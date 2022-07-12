package com.example.project1;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddUserToGroup {
    @FXML
    private TextField nameField = new TextField();
    private Register register;
    private String registerName = "";
    private String groupName = "";
    @FXML
    Label label = new Label();
    @FXML
    private Button addUser = new Button();

    public void recieveRegister(Register register) {
        this.register = register;
    }
    @FXML
    public void enterName() {
        this.registerName = this.nameField.getText();
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public void add() {
        ArrayList<Register> registers = new ArrayList<>();
        registers = this.register.getRegisters();
        System.out.println(registers.size());
        Register add = null;
        boolean check = false;
        for (Register register : registers) {
            if (register.getUserName().equals(this.registerName)) {
                add = register;
                check = true;
                break;
            }
        }
        if (check == true) {
            System.out.println(registers.size());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("userAbilities.fxml"));
                Parent root = loader.load();
                UserAbilities userAbilities = loader.getController();
                userAbilities.recieveRegister(add);
                userAbilities.addGroup(this.groupName, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
           try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Group.fxml"));
                Parent root = loader.load();
                Group group = loader.getController();
                group.setNameOfGroup(groupName);
                group.setRegister(this.register);
                group.addUSer(add);
            } catch (IOException e) {
                e.printStackTrace();
            }
           this.label.setTextFill(Color.GREEN);
           label.setText("added succufully");
        } else {
            this.label.setTextFill(Color.RED);
            this.label.setText("there is not a user white this user name");
        }
    }
    @FXML
    public void back(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Group.fxml"));
            Parent root = loader.load();
            Group group = loader.getController();
            group.setNameOfGroup(this.groupName);
            group.setRegister(this.register);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

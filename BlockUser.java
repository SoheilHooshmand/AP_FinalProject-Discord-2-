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

public class BlockUser {

    private Register register;
    private String  groupName;
    private ArrayList<Register> users = new ArrayList<>();
    private Register blockUser;
    private String blockUserName;
    @FXML
    private TextField textField = new TextField();
    @FXML
    private Button bolck = new Button();
    @FXML
    private Button back = new Button();
    @FXML
    private Label label = new Label();

    public void recieveRegister(Register register) {
        this.register = register;
    }

    public void recieveGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void recieveUsers(ArrayList<Register> users) {
        this.users = users;
    }

    @FXML
    public void setBlockUserName() {
        this.blockUserName = this.textField.getText();
    }
    @FXML
    public void blockUser(Event event) {
        boolean check = false;
        for (Register register1 : users) {
            if (register1.getUserName().equals(this.blockUserName)) {
                this.blockUser = register1;
                check = true;
                break;
            }
        }
        if (check == false) {
            label.setTextFill(Color.RED);
            label.setText("this user name does't exit in this group");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Group.fxml"));
                Parent root = loader.load();
                Group group = loader.getController();
                group.setNameOfGroup(this.groupName);
                group.setRegister(this.register);
                group.recieveBlock(this.blockUser);
                /*Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();*/
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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

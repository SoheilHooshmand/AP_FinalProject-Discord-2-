package com.example.project1;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ShowGroupUsers {

    private ArrayList<Register> groupUsers;
    private Register register;
    private String groupName;
    @FXML
    private VBox vBox = new VBox();

    public void recieveRegister(Register register) {
        this.register = register;
    }

    public void reciveUsersList(ArrayList<Register> groupUsers) {
        this.groupUsers = groupUsers;
    }

    public void reciveGroupName(String groupName) {
        this.groupName = groupName;
    }
    @FXML
    public void show(Event event) {
        for (int i = 0; i < this.groupUsers.size(); i++) {
            Label label = new Label(groupUsers.get(i).getUserName());
            vBox.getChildren().add(label);
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
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

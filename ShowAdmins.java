package com.example.project1;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ShowAdmins {

    @FXML
    private VBox vBox = new VBox();
    private Register register;
    private String groupName;
    private ArrayList<Register> admins = new ArrayList<>();
    @FXML
    Button back = new Button();

    public void recieveRegister(Register register) {
        this.register = register;
    }
    public void recieveGroupName(String groupName) {
        this.groupName = groupName;
    }
    public void recieveAdminsList(ArrayList<Register> admins) {
        this.admins = admins;
    }
    @FXML
    public void show() {
        for (int i = 0; i < admins.size(); i++) {
            Label label = new Label();
            label.setText(admins.get(i).getUserName());
            vBox.getChildren().add(label);
        }
    }
    @FXML
    public void backGroup(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Group.fxml"));
            Parent root = loader.load();
            Group group = loader.getController();
            group.setRegister(this.register);
            group.setNameOfGroup(this.groupName);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

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

public class ShowBlockList {
    @FXML
    private VBox vBox = new VBox();
    private ArrayList<Register> blocks = new ArrayList<>();
    private Register register;
    @FXML
    private Button back = new Button();
    @FXML
    private Button show = new Button();

    public void setRegister(Register register) {this.register = register;}
    public void setBlocks(ArrayList<Register> blocks) {this.blocks = blocks;}

    @FXML
    public void show() {
        for (Register register : blocks) {
            Label label = new Label();
            label.setText(register.getUserName());
            vBox.getChildren().add(label);
        }
    }
    @FXML
    public void back(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userAbilities.fxml"));
            Parent root = loader.load();
            UserAbilities userAbilities = loader.getController();
            userAbilities.recieveRegister(this.register);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

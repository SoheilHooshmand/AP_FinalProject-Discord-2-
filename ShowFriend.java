package com.example.project1;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ShowFriend {
    private Register register;
    @FXML
    private VBox vBox = new VBox();
    private ArrayList<Register> friends = new ArrayList<>();
    @FXML
    private Button back = new Button();

    public void reciveFriends(ArrayList<Register> friends) {this.friends = friends;}
    public void receiveRegster(Register register) {this.register = register;}
    @FXML
    public void show() {
        for (int i = 0; i < friends.size(); i++) {
            Label label = new Label(friends.get(i).getUserName());
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

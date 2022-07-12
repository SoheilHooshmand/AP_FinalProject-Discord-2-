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

public class BlockUserFromPV {
    @FXML
    private TextField textField = new TextField();
    @FXML
    private Button block = new Button();
    private Register register;
    private ArrayList<Register> registers = new ArrayList<>();
    private Register blockRegister;
    private String chosenPerson;
    @FXML
    private Label label = new Label();
    @FXML
    private Button back = new Button();

    public void setRegister(Register register) {this.register = register;registers = this.register.getRegisters();}
    @FXML
    public void setChosenPerson() {this.chosenPerson = this.textField.getText();}
    @FXML
    public void block(Event event) {
        boolean check = false;
        for (Register register : registers) {
            if (register.getUserName().equals(chosenPerson)) {
                check = true;
                blockRegister = register;
                break;
            }
        }
        if (check == true) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("userAbilities.fxml"));
                Parent root = loader.load();
                UserAbilities userAbilities = loader.getController();
                userAbilities.recieveRegister(this.register);
                userAbilities.receiveBlock(blockRegister);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            label.setTextFill(Color.RED);
            label.setText("There is not a person white this username.");
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

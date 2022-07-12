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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class GoToPersonalChat {

    private Register register;
    private String nameOfgues;
    private ArrayList<Register> users = new ArrayList<>();
    @FXML
    private TextField textField = new TextField();
    @FXML
    private Button button = new Button();
    @FXML
    private Label label = new Label();
    @FXML
    private Button back = new Button();

    public void setRegister(Register register) {
        this.register = register;
        this.users = register.getRegisters();
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
    @FXML
    public void setNameOfgues() {
        this.nameOfgues = this.textField.getText();
    }
    @FXML
    public void check(Event event) {
        boolean check = false;
        boolean check1 = true;
        ArrayList<Register> blocks = new ArrayList<>();
        for (Register register : users) {
            if (register.getUserName().equals(this.nameOfgues)){
                check = true;
                break;
            }
        }
        if (check == true) {
            File file = new File(nameOfgues + "Blocksss.binary");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                blocks = (ArrayList<Register>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            for (Register register : blocks) {
                System.out.println("()" + register.getUserName());
            }
            for (Register register : blocks) {
                if (register.getUserName().equals(this.register.getUserName())) {
                    check1 = false;
                    break;
                }
            }
        }
        if (check == false) {
            this.label.setTextFill(Color.RED);
            this.label.setText("there is not a person whit this user name.");
        } /*else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("personalChat.fxml"));
                Parent root = loader.load();
                PersonalChat personalChat = loader.getController();
                personalChat.reiciveRegister(this.register);
                personalChat.reciveGuest(this.nameOfgues);
                //System.out.println("()" + nameOfgues);
                personalChat.setClient();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        if (check1 == false) {
            label.setTextFill(Color.RED);
            label.setText("you are in his/her block list");
        }
        if (check == true && check1 == true) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("personalChat.fxml"));
                Parent root = loader.load();
                PersonalChat personalChat = loader.getController();
                personalChat.reiciveRegister(this.register);
                personalChat.reciveGuest(this.nameOfgues);
                //System.out.println("()" + nameOfgues);
                personalChat.setClient();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

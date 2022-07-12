package com.example.project1;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowGroupRequestes {
    private ArrayList<String > groupa = new ArrayList<>();
    @FXML
    private VBox vBox = new VBox();
    @FXML
    private Button show = new Button();
    @FXML
    private Button back = new Button();
    private Register register;
    public void receiveRegister(Register register) {
        this.register = register;
        System.out.println("%^&" + register.getUserName());
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
    public void show(Event event) {
        File file = new File(register.getUserName() + "JoinGroup.binay");
        File file1 = new File(register.getUserName() + "ShowGroupJoinGroup.binary");
        ArrayList<String> mainNames = new ArrayList<>();
        if (file.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                mainNames = (ArrayList<String>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (file1.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file1);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                groupa = (ArrayList<String>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException| ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("@!#" + groupa.size());
            for (int i = 0; i < groupa.size(); i++) {
                Label label = new Label(groupa.get(i));
                vBox.getChildren().add(label);
                ArrayList<String> finalMainNames = mainNames;
                int finalI = i;
                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("answerGroupRequests.fxml"));
                            Parent root = loader.load();
                            AnswerGroupRequests answerGroupRequests = loader.getController();
                            answerGroupRequests.setRegister(register);
                            answerGroupRequests.setGroupName(finalMainNames.get(finalI));
                            answerGroupRequests.setShowGroup(label.getText());
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } else {
            System.out.println("ghk");
        }
    }

    public void ad() {
        try {
            VBox vBox = new VBox();
            Client client = new Client(new Socket("127.0.0.1" ,6000));
            client.listening(vBox);
            client.setNameOfClient(this.register.getUserName());
            client.setFriendOrGroup("group");
            client.intialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

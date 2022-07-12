package com.example.project1;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

//import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RemoveChannal {

    private Register register;
    private ArrayList<String> channals = new ArrayList<>();
    private ArrayList<CreateChannal> a = new ArrayList<>();
    @FXML
    private VBox vBox1 = new VBox();
    @FXML
    private Button show1 = new Button();
    @FXML
    private Label label1 = new Label();
    private String groupName;
    @FXML
    private Button removeChannal1 = new Button();
    @FXML
    private Button back = new Button();

    public void recieveRegister(Register register) {this.register = register;}
    //public void receiveChannal(ArrayList<CreateChannal> channals) {this.channals = channals;}
    public void receiveGroupName(String  groupName) {
        this.groupName = groupName;
        File file = new File(groupName + "ShowChannalNames.binay");
        File file1 = new File(groupName + "Channalssss.binary");
        if (file1.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file1);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                a = (ArrayList<CreateChannal>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (file.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                channals = (ArrayList<String>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void show() {
        for (int i = 0; i < channals.size(); i++) {
            System.out.println(channals.get(i));
            Button button = new Button(channals.get(i));
            vBox1.getChildren().add(button);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int j = 0;
                    for ( j = 0; j < channals.size(); j++) {
                        if (channals.get(j).equals(button.getText())) {
                            channals.remove(j);
                            a.remove(j);
                        }
                    }
                    File file  = new File(groupName +"Channalssss.binary");
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                        out.writeObject(a);
                        fileOutputStream.close();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    File file1 = new File(groupName + "ShowChannalNames.binay");
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file1);
                        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                        out.writeObject(channals);
                        fileOutputStream.close();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
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
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}

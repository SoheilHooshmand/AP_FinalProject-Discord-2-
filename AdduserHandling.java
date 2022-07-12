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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdduserHandling implements Initializable{

    @FXML
    private Button show = new Button();
    @FXML
    private VBox vBox = new VBox();
    private Register register;
    private ArrayList<Register> friends = new ArrayList<>();
    private Client client;
    private String groupname;
    private String showGroup;
    @FXML
    private Button back = new Button();
    public void recivieShowGroup(String showGroup) {this.showGroup = showGroup;}
    public void receiveGroupName(String groupname) {this.groupname = groupname;}
    public void recieveRegisetr(Register register) {this.register = register;}
    public void setClint() {
       /* try {
            client = new Client(new Socket("127.0.0.1", 6000));
            client.setNameOfClient(this.register.getUserName());
            client.setFriendOrGroup("group");
            client.setGroupName(this.groupname);
            client.intialize();
            client.setRegister(this.register);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        client.setNameOfClient(this.register.getUserName());
        client.setFriendOrGroup("group");
        client.setGroupName(this.groupname);
        client.intialize();
        client.setRegister(this.register);
    }
    @FXML
    public void back(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Group.fxml"));
            Parent root = loader.load();
            Group group = loader.getController();
            group.setNameOfGroup(this.groupname);
            group.setRegister(this.register);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
   public void showAndSend() {
        VBox vBox = new VBox();
        client.listening(vBox);
        File file = new File(register.getUserName() + "Friendsss.binary");
        if (file.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                friends = (ArrayList<Register>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        //System.out.println("@#" + friends.size());
        for (int i = 0; i < friends.size(); i++) {
            Label label = new Label(friends.get(i).getUserName());
            this.vBox.getChildren().add(label);
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Message message = new Message(groupname , register.getUserName(), "group");
                    message.setGuess(label.getText());
                    message.setShowGroup(showGroup);
                    System.out.println("@#" + showGroup + "@#");
                    client.setGuest(label.getText());
                    message.setGroupName(groupname);
                    client.sentMessage(message);
                }
            });
        }
   }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            client = new Client(new Socket("127.0.0.1", 6000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

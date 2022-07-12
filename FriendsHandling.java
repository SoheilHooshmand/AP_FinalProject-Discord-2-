package com.example.project1;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FriendsHandling implements Initializable {
    @FXML
    private TextField textField = new TextField();
    @FXML
    private VBox friends = new VBox();
    @FXML
    private VBox recieveRequests = new VBox();
    @FXML
    private Button sentReguest = new Button();
    @FXML
    private Button back = new Button();
    private String nameOfF = "";
    private Register register;
    private File file;
    private ArrayList<Register> acceptedFriends = new ArrayList<>();
    private ArrayList<HBox> UnAcceptedFriends = new ArrayList<>();
    private ArrayList<String> requests = new ArrayList<>();
    private Client client;
    @FXML
    private Button show = new Button();
    @FXML
    public void nameOfNewFriend() {
        this.nameOfF = this.textField.getText();
    }
    public void receiveRegister(Register register) {this.register = register;}
    public void setClint() {
        client.setNameOfClient(this.register.getUserName());
        client.setFriendOrGroup("friend");
        client.intialize();
        client.setRegister(this.register);
    }
    public void delete(String s) {
        file = new File(this.register.getUserName() + "receiverRequest.binary");
        try {
            FileInputStream fileInputStream =  new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            requests = (ArrayList<String>) in.readObject();
            fileInputStream.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        requests.remove(s);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(requests);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   @FXML
    public void show() {
        file = new File(this.register.getUserName() + "receiverRequest.binary");
        if (file.length() == 0) {
            try {
                System.out.println("#000#");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(requests);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                requests = (ArrayList<String>) in.readObject();
                System.out.println("#" + requests.size() + "#");
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<HBox> hBoxes = new ArrayList<>();
            for (int i = 0; i < requests.size(); i++) {
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5, 5, 5, 10));
                Text text = new Text(requests.get(i));
                TextFlow textFlow = new TextFlow(text);
                text.setStyle("-fx-background-color: rgb(233, 233, 235);" + "-fx-backgroud-radius: 20px;");
                textFlow.setPadding(new Insets(5, 10, 5, 10));
                hBox.getChildren().add(textFlow);
                hBoxes.add(hBox);
            }
                    for (int i = 0; i < hBoxes.size(); i++) {
                        recieveRequests.getChildren().add(hBoxes.get(i));
                        int finalI = i;
                        hBoxes.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("answerRequest.fxml"));
                                    Parent root = loader.load();
                                    AnswerRequest answerRequest = loader.getController();
                                    System.out.println("#$" + register.getUserName());
                                    answerRequest.setRegister(register);
                                    answerRequest.setNameOfFriend(requests.get(finalI));
                                    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                                    Scene scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    // vBox.getChildren().add(hBox);

        }

    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            client = new Client(new Socket("127.0.0.1", 6000));
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* vBoxMessage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                spMain.setVvalue((Double) t1);
            }
        });*/
        client.listening(recieveRequests);
        sentReguest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String message = textField.getText();
                if (!(message.isEmpty())) {
                    Message message1 = new Message("be friend", register.getUserName(), "friend");
                    message1.setGuess(message);
                   // message1.setGroupName(groupName);
                   /* HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5, 5, 5, 10));
                    Text text = new Text(message1.toString());
                    TextFlow textFlow = new TextFlow(text);
                    textFlow.setStyle("-fx-color: rgb(239, 242, 255);" + "-fx-background-color: rgb(15, 125, 242);" + "-fx-background-radius: 20px;");
                    textFlow.setPadding(new Insets(5, 10, 5, 10));
                    text.setFill(Color.color(0.943, 0.945, 0.996));
                    hBox.getChildren().add(textFlow);
                    recieveRequests.getChildren().add(hBox);*/
                    client.sentMessage(message1);
                    textField.clear();
                }
            }
        });
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
    public void addAbale(VBox vBox, Message message) {
        file = new File(register.getUserName() + "receiverRequest.binary");
        if (file.length() == 0) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(requests);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                requests = (ArrayList<String>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        requests.add(message.getAuther());
        System.out.println("*" + requests.size() +"*");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(requests);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       // ArrayList<HBox> hBoxes = new ArrayList<>();
        ArrayList<HBox> hBoxes = new ArrayList<>();
        for (int i = 0; i < requests.size(); i++) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5, 5, 5, 10));
            Text text = new Text(requests.get(i));
            TextFlow textFlow = new TextFlow(text);
            text.setStyle("-fx-background-color: rgb(233, 233, 235);" + "-fx-backgroud-radius: 20px;");
            textFlow.setPadding(new Insets(5, 10, 5, 10));
            hBox.getChildren().add(textFlow);
            hBoxes.add(hBox);
        }
        //this.UnAcceptedFriends.add(hBox);
       // Platform.runLater(new Runnable() {
           // @Override
           // public void run() {
                for (int i = 0; i < hBoxes.size(); i++) {
                    vBox.getChildren().add(hBoxes.get(i));
                    int finalI = i;
                    hBoxes.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("answerRequest.fxml"));
                                Parent root = loader.load();
                                AnswerRequest answerRequest = loader.getController();
                                System.out.println("#$" + register.getUserName());
                                answerRequest.setRegister(register);
                                answerRequest.setNameOfFriend(requests.get(finalI));
                                Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                   // vBox.getChildren().add(hBox);
           // }
       // });
    }
}


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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PersonalChat implements Initializable {

    private Register register;
    private String guest;
    private Client client;
    @FXML
    private TextField tfMessage = new TextField();
    @FXML
    private Button sentMessage = new Button();
    @FXML
    private VBox vBoxMessage = new VBox();
    @FXML
    private ScrollPane spMain = new ScrollPane();
    @FXML
    private Label title = new Label();
    @FXML
    private Button back = new Button();
    @FXML
    private ImageView imageView  = new ImageView();
    @FXML
    private Button sentFile = new Button();
    public void reiciveRegister(Register register) {
        this.register = register;

    }
    public void reciveGuest(String guest) {
        this.guest = guest;
        this.title.setText(guest);
        File file = new File(guest + "profileeeee.binary");
        ArrayList<String> s = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            s = (ArrayList<String>) in.readObject();
            fileInputStream.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (s.size() > 0 && s.get(0).equals("") == false) {
            Image image = new Image(s.get(0));
             imageView.setImage(image);
        }
    }
    public void setClient() {client.setNameOfClient(this.register.getUserName());client.setGuest(this.guest);client.intialize();}
    @FXML
    public void back(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("goToPersonalMessage.fxml"));
            Parent root = loader.load();
            GoToPersonalChat goToPersonalChat = loader.getController();
            goToPersonalChat.setRegister(this.register);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            client = new Client(new Socket("127.0.0.1", 6000));
        } catch (IOException e) {
            e.printStackTrace();
        }
        vBoxMessage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                spMain.setVvalue((Double) t1);
            }
        });
        client.listening(vBoxMessage);
        sentFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Message message = new Message("", "", "");
               /* ObjectOutputStream outputStream = null;
                try {
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                    System.out.println("!!" + groupName);
                    System.out.println("!!" + register.getUserName());
                    System.out.println("!!" + channalName);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                final File[] fileToSend = new File[1];
                JFrame frame = new JFrame("Client");
                frame.setSize(450, 450);
                frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
                frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

                JLabel jlTitle = new JLabel("File sender");
                jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
                jlTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
                jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel jlFileName = new JLabel("Choose a file to sent");
                jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
                jlFileName.setBorder(new EmptyBorder(50, 0, 0, 0));
                jlFileName.setAlignmentX(Component.CENTER_ALIGNMENT);

                JPanel jpButton = new JPanel();
                jpButton.setBorder(new EmptyBorder(75, 0, 10 ,0));

                JButton jbSendFile = new JButton("Send file");
                jbSendFile.setPreferredSize(new Dimension(150, 75));
                jbSendFile.setFont(new Font("Arial", Font.BOLD, 20));

                JButton jbChooseFile = new JButton("Choose file");
                jbChooseFile.setPreferredSize(new Dimension(150, 75));
                jbChooseFile.setFont(new Font("Arial", Font.BOLD, 20));

                jpButton.add(jbSendFile);
                jpButton.add(jbChooseFile);

                jbChooseFile.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        JFileChooser jFileChooser = new JFileChooser();
                        jFileChooser.setDialogTitle("choose a file to sent");
                        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                            fileToSend[0] = jFileChooser.getSelectedFile();
                            jlFileName.setText("The file you want to send is: " + fileToSend[0].getName());
                        }
                    }
                });
                // Socket finalSocket = socket;
                //ObjectOutputStream finalOutputStream = outputStream;
                final Message[] massageToSent = {null};
                jbSendFile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        if (fileToSend[0] == null) {
                            jlFileName.setText("please choose a file  frist");
                        } else  {
                            try {
                                FileInputStream fileInputStream = new FileInputStream(fileToSend[0].getAbsolutePath());
                                //Socket socket = new Socket("127.0.0.1", 6000);
                                //DataOutputStream dataOutputStream = new DataOutputStream(finalSocket.getOutputStream());
                                String fileName = fileToSend[0].getName();
                                byte[] fileNameByte = fileName.getBytes();
                                byte[] fileContentBytes = new byte[(int) fileToSend[0].length()];
                                fileInputStream.read(fileContentBytes);
                                Message file = new Message("", register.getUserName(), "private");
                                file.setNameOfFile(fileName);
                                file.setDeta(fileContentBytes);
                                //file.setGroupName(groupName);
                                file.setGuess(guest);
                                file.setHost(register.getUserName());
                                client.sentMessage(file);
                                System.out.println("######" + file.getNameOfFile());
                                //client.sentMessage(file);
                                // dataOutputStream.writeInt(fileNameByte.length);
                                // dataOutputStream.write(fileNameByte);
                                //dataOutputStream.writeInt(fileContentBytes.length);
                                //dataOutputStream.write(fileContentBytes);
                            } catch (IOException es) {
                                es.printStackTrace();
                            }
                        }
                    }
                });
                frame.add(jlTitle);
                frame.add(jlFileName);
                frame.add(jpButton);
                frame.setVisible(true);
                //client.sentMessage(message);
            }
        });
        sentMessage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String message = tfMessage.getText();
                if (!(message.isEmpty())) {
                    Message message1 = new Message(message, register.getUserName(), "private");
                   // message1.setGroupName(groupName);
                    message1.setGuess(guest);
                    message1.setHost(register.getUserName());
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5, 5, 5, 10));
                    Text text = new Text(message1.toString());
                    TextFlow textFlow = new TextFlow(text);
                    textFlow.setStyle("-fx-color: rgb(239, 242, 255);" + "-fx-background-color: rgb(15, 125, 242);" + "-fx-background-radius: 20px;");
                    textFlow.setPadding(new Insets(5, 10, 5, 10));
                    text.setFill(Color.color(0.943, 0.945, 0.996));
                    hBox.getChildren().add(textFlow);
                    vBoxMessage.getChildren().add(hBox);
                    client.sentMessage(message1);
                    tfMessage.clear();
                }
            }
        });
    }

    public static void addAbale(VBox vBox, Message message) {

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 10));

        Text text = new Text(message.toString());
        TextFlow textFlow = new TextFlow(text);
        text.setStyle("-fx-background-color: rgb(233, 233, 235);" + "-fx-backgroud-radius: 20px;");
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        hBox.getChildren().add(textFlow);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
            }
        });
    }

    public static void addFile(VBox vBox, Message message) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 10));

        Text text = new Text(message.getNameOfFile());
        TextFlow textFlow = new TextFlow(text);
        text.setStyle("-fx-background-color: rgb(233, 233, 235);" + "-fx-backgroud-radius: 20px;");
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        hBox.getChildren().add(textFlow);
        textFlow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                File file = new File(message.getNameOfFile());
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(message.getDeta());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //vBox.getChildren().add(hBox);
        System.out.println("############################################");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
            }
        });
    }
}

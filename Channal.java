package com.example.project1;

import javafx.beans.value.ObservableBooleanValue;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

import java.net.URL;
import java.util.ResourceBundle;

public class Channal implements Initializable {

    @FXML
    private ScrollPane spMain = new ScrollPane();
    @FXML
    private VBox vBoxMessage = new VBox();
    @FXML
    private Button sentFile = new Button();
    @FXML
    private Button sentMessage = new Button();
    @FXML
    private TextField tfMessage = new TextField();
    private Client client = null;
    private String groupName;
    private Register register;
    @FXML
    private TextField textField = new TextField();
    @FXML
    private Button changeName = new Button();
    @FXML
    private Button back = new Button();
    String newName = "";
    private String channalName;
    private String showing;
    private Socket socket;
    @FXML
    public void setNewName() {this.newName = this.textField.getText();}
    public void reciveveChannalName(String channalName) {this.channalName = channalName;}
    public void setRegister(Register register) {
        this.register = register;
    }
    public void setGroupName(String  groupName) {
        this.groupName = groupName;
    }
   public void setClinetGroup() {
            client.setNameOfGroup(this.groupName);
            client.setNameOfClient(this.register.getUserName());
            client.setChannalName(this.channalName);
            client.intialize();

   }
   public void setShowing(String s) {this.showing = s;}
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void sendFile() {
       // client.cover();
        //Socket socket = null;
        ObjectOutputStream outputStream = null;
        try {
             //socket = new Socket("127.0.0.1", 6000);
             outputStream = new ObjectOutputStream(socket.getOutputStream());
             System.out.println("!!" + groupName);
             System.out.println("!!" + register.getUserName());
             System.out.println("!!" + channalName);
             //outputStream.writeObject("");
             //outputStream.writeObject("");
             //outputStream.writeObject(groupName);
            // outputStream.writeObject(this.register.getUserName());
             //outputStream.writeObject(channalName);
             //outputStream.writeObject("");
            // outputStream.writeObject("");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        Socket finalSocket = socket;
        ObjectOutputStream finalOutputStream = outputStream;
        jbChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (fileToSend[0] == null) {
                    jlFileName.setText("please choose a file  frist");
                } else  {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(fileToSend[0].getAbsolutePath());
                        //Socket socket = new Socket("127.0.0.1", 6000);
                        DataOutputStream dataOutputStream = new DataOutputStream(finalSocket.getOutputStream());
                        String fileName = fileToSend[0].getName();
                        byte[] fileNameByte = fileName.getBytes();

                        byte[] fileContentBytes = new byte[(int) fileToSend[0].length()];
                        fileInputStream.read(fileContentBytes);
                        finalOutputStream.writeObject("");
                        finalOutputStream.writeObject("");
                        //MyFile file = new MyFile(fileName , fileNameByte.length, fileContentBytes, register.getUserName());
                        Message file = new Message("", register.getUserName(), "public");
                        file.setNameOfFile(fileName);
                        file.setDeta(fileContentBytes);
                        file.setChannalName(channalName);
                        //client.sentMessage(file);

                       // dataOutputStream.writeInt(fileNameByte.length);
                       // dataOutputStream.write(fileNameByte);

                        //dataOutputStream.writeInt(fileContentBytes.length);
                        //dataOutputStream.write(fileContentBytes);
                        finalOutputStream.writeObject(file);
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
    }
    @FXML
    public void rename(Event event) {
        if (newName.equals("") == false) {
            ArrayList<String> showchannal = new ArrayList<>();
            ArrayList<CreateChannal> channals = new ArrayList<>();
            boolean check = true;
            File file = new File(groupName + "Channalssss.binary");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                channals = (ArrayList<CreateChannal>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            for (CreateChannal createChannal : channals) {
                if (createChannal.getChanalname().equals(newName)) {
                    check = false;
                    break;
                }
            }
            if (check == true) {
                File file1 = new File(groupName + "ShowChannalNames.binay");
                try {
                    FileInputStream fileInputStream = new FileInputStream(file1);
                    ObjectInputStream in = new ObjectInputStream(fileInputStream);
                    showchannal = (ArrayList<String>) in.readObject();
                    fileInputStream.close();
                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < showchannal.size(); i++) {
                    if (showchannal.get(i).equals(showing)) {
                        showchannal.set(i, newName);
                        break;
                    }
                }
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file1);
                    ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                    out.writeObject(showchannal);
                    fileOutputStream.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            socket = new Socket("127.0.0.1", 6000);
            client = new Client(socket);
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
                                Message file = new Message("", register.getUserName(), "public");
                                file.setNameOfFile(fileName);
                                file.setDeta(fileContentBytes);
                                file.setGroupName(groupName);
                                file.setChannalName(channalName);
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
                    Message message1 = new Message(message, register.getUserName(), "public");
                    message1.setGroupName(groupName);
                    message1.setChannalName(channalName);
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

        Text text = new Text(message.getAuther() + ": " + message.getNameOfFile() + "(" + message.getLocalDateTime());
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

package com.example.project1;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class EnterAccunt implements Initializable {
    @FXML
    private TextField userNameField = new TextField();
    @FXML
    private PasswordField passwordField = new PasswordField();
    @FXML
    private Label label = new Label();
    private String userName = "";
    private String password = "";
    private ArrayList<Register> registers = new ArrayList<>();
    private File file;

    @FXML
    public void recieveUserName() {this.userName = this.userNameField.getText();}
    @FXML
    public void recievePassword() {this.password = this.passwordField.getText();}

    @FXML
    public void enter(Event event) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            registers = (ArrayList<Register>) in.readObject();
            fileInputStream.close();;
            in.close();
        } catch (IOException  | ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean check = false;
        for (Register register : registers) {
            if (register.getUserName().equals(this.userName) && register.getPassword().equals(this.password)) {
                check = true;
                break;
            }
        }
        if (check == false) {
            label.setTextFill(Color.RED);
            label.setText("There is not accunt white this user name and password");
            this.userNameField.setText("");
            this.passwordField.setText("");
        } else {
            label.setTextFill(Color.GREEN);
            label.setText("welcom");
            Register register = null;
            for (Register register1 : registers) {
                if (register1.getUserName().equals(this.userName) && register1.getPassword().equals(this.password)) {
                    register = register1;
                }
            }
           try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("userAbilities.fxml"));
                Parent root = loader.load();
                UserAbilities userAbilities = loader.getController();
                userAbilities.recieveRegister(register);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void switchRegister(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void reciveAccunts (ArrayList<Register> registers) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(registers);
            fileOutputStream.close();
            out.close();
            System.out.println(registers.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        file = new File("accuntsssssssList.binary");
        if (file.length() == 0) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(registers);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                registers = (ArrayList<Register>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Register> getRegisters() {
        try {
            FileInputStream fileInputStream = new FileInputStream("accuntsssssssList.binary");
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            registers = (ArrayList<Register>) in.readObject();
            fileInputStream.close();
            in.close();
        } catch (IOException |  ClassNotFoundException e) {
            e.printStackTrace();
        }
        return registers;
    }
}

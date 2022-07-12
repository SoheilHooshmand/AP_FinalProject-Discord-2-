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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccuntController implements Initializable , Serializable{
    @FXML
    private transient TextField userNameField = new TextField();
    @FXML
    private transient PasswordField passwordField = new PasswordField();
    @FXML
    private transient TextField emailField = new TextField();
    @FXML
    private transient TextField phoneNumberField = new TextField();
    @FXML
    private transient Label label = new Label();
    @FXML
    private transient ImageView imageView = new ImageView();
    @FXML
    private Label la = new Label();
    private String userName = "";
    private String password = "";
    private String email = "";
    private String phoneNumber = "";
    private Image image;
    private static ArrayList<AccuntController> accuntControllers = new ArrayList<>();
    private File file = new File("allAccunts.binary");


    @FXML
    public void setUserName() {this.userName = this.userNameField.getText();}
    @FXML
    public void setPassword() {this.password = this.passwordField.getText();}
    @FXML
    public void setEmail() {this.email = this.emailField.getText();}
    @FXML
    public void setPhoneNumber() {this.phoneNumber = this.phoneNumberField.getText();}
    @FXML
    public void setImage() {this.image = this.imageView.getImage();}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       /* file = new File("allAccunts.binary");
        if (file.length() == 0) {
            try {
                System.out.println(file.length());
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(accuntControllers);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println(file.length());
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                accuntControllers = (ArrayList<AccuntController>) in.readObject();
                fileInputStream.close();
                in.close();
                System.out.println(accuntControllers.size());
                //System.out.println(accuntControllers.get(0).getUserName());
            } catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }*/
    }
    public boolean handleUserName(String userName) {
        boolean check = false;
        int count = 0;
        if(userName.length() >= 6){
            int counter = 0;
            for (int i = 0; i < userName.length() ; i++) {
                //48 ta 57    65 ta 90   97 ta 122
                if ((userName.charAt(i) >= 48 && userName.charAt(i) <= 57) || (userName.charAt(i) >= 65 && userName.charAt(i) <= 90) || (userName.charAt(i) >= 97 && userName.charAt(i) <= 122)){
                    counter++;
                }
            }
            if (counter == userName.length()){
                count++;
            }
        }

        int c = 0;
        for(AccuntController accunt : accuntControllers) {
            if (accunt.userName.equals(userName) == false) {
                c++;
            }
        }
        if (c == accuntControllers.size()) {
            count++;
        }
        //System.out.print(count);
        if (count == 2) {
            check = true;
        }
        return  check;
    }

    public boolean handlePassword(String password) {
        boolean check = false;
        int conter = 0;
        int cont = 0;
        if (password.length() >= 8) {
            for (int i = 0; i < password.length(); i++) {
                if ((password.charAt(i) >= 65 && password.charAt(i) <= 90) || (password.charAt(i) >= 97 && password.charAt(i) <= 122) || (password.charAt(i) >= 48 && password.charAt(i) <= 57)) {
                    //check = true;
                    // this.passwordd = password;
                    cont++;
                }
            }
        }
        if (cont == password.length()) {
            conter++;
        }
        int c = 0;
        for (AccuntController accunt : accuntControllers) {
            if (accunt.password.equals(password) == false) {
                c++;
            }
        }
        if (c == accuntControllers.size()) {
            conter ++;
        }
        if (conter == 2) {
            check = true;
        }
        return check;
    }

    public boolean handleEmail(String email) {
        boolean check = false;
        boolean regerx = false;
        Pattern myEmail = Pattern.compile("@gmail.com");
        //System.out.println("Enter Email\nForm: *********@gmail.com");
        //Scanner in = new Scanner(System.in);
        //String email = in.nextLine();
        Matcher myMatch=myEmail.matcher(email);
        //regex
        if(myMatch.find()&&(email.substring(email.length()-10)).equals("@gmail.com")) {
            //this.emaill = email;
            check = true;
        }

        return  check;
    }

    public boolean handlePhoneNuber(String phoneNumber) {
        boolean check = false;
        if (phoneNumber.equals("")) {
            check = true;
        } else if (phoneNumber.charAt(0) == (char) 0 && phoneNumber.charAt(1) == (char) 9){
            int counter = 0;
            for (int i = 2; i < 11; i++) {
                if (phoneNumber.charAt(i) >= 48 && phoneNumber.charAt(i) <= 57) {
                    //System.out.println("Not Valid\nTry Again");
                    counter++;
                }
            }
            if (counter == 11) {
                check = true;
                // this.phoneNumberr = phoneNumber;
            }
        }
        return check;
    }

    @FXML
    public void check(Event event) {
        if (handleUserName(this.userName) && handlePassword(this.password) && handleEmail(this.email) && handlePhoneNuber(this.phoneNumber)) {
            accuntControllers.add(this);
            la.setTextFill(Color.GREEN);
            la.setText("succufully");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(accuntControllers);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (handleUserName(this.userName) == false) {
            this.la.setTextFill(Color.RED);
            this.la.setText("Invald user name");
            this.userNameField.setText("");
            this.passwordField.setText("");
            this.emailField.setText("");
            this.phoneNumberField.setText("");
        } else if (handlePassword(this.password) == false) {
            this.la.setTextFill(Color.RED);
            this.la.setText("Invald password");
            this.userNameField.setText("");
            this.passwordField.setText("");
            this.emailField.setText("");
            this.phoneNumberField.setText("");
        } else if (handleEmail(this.email) == false) {
            la.setTextFill(Color.RED);
            la.setText("Invlad email");
            userNameField.setText("");
            passwordField.setText("");
            emailField.setText("");
            phoneNumberField.setText("");
        } else if (handlePhoneNuber(this.phoneNumber)) {
            la.setTextFill(Color.RED);
            la.setText("Invalid phonenumber");
            this.userNameField.setText("");
            this.passwordField.setText("");
            this.emailField.setText("");
            this.phoneNumberField.setText("");
        }
    }
    public void swi(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public String getUserName() {return this.userName;}
    public String getPassword() {return this.password;}
}

package com.example.project1;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register implements Serializable , Initializable {

    @FXML
    private transient TextField userNameField = new TextField();
    @FXML
    private transient PasswordField passwordField = new PasswordField();
    @FXML
    private transient TextField emailFiled = new TextField();
    @FXML
    private transient TextField phoneNumberField = new TextField();
    @FXML
    private transient Label label = new Label();
    @FXML
    private transient ImageView imageView = new ImageView();
    private String userName = "";
    private String password = "";
    private String email = "";
    private String phoneNumber = "";
    private String imageAdress = "";
    private File saveImage;
    @FXML
    private transient TextField receiveImageAddress = new TextField();
    private transient Image image;
    private ArrayList<Register> registers = new ArrayList<>();
    private File file ;
    private transient Button setImage = new Button();

    @FXML
    public void setImage() {
        AtomicReference<String> path = new AtomicReference<>("");
        JFrame frame = new JFrame("Client");
        frame.setSize(450, 450);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        JLabel jlTitle = new JLabel("Chose Picture");
        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel jpButton = new JPanel();
        jpButton.setBorder(new EmptyBorder(75, 0, 10 ,0));

        JButton jbChoseImage = new JButton("Chose Image");
        jbChoseImage.setPreferredSize(new Dimension(150, 75));
        jbChoseImage.setFont(new Font("Arial", Font.BOLD, 20));

        jpButton.add(jbChoseImage);

        jbChoseImage.addActionListener((ActionListener) (e) -> {

            JFileChooser file = new JFileChooser();
            file.setCurrentDirectory(new File(System.getProperty("user.home")));
            //filtering files
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","png");
            file.addChoosableFileFilter(filter);
            int res = file.showSaveDialog(null);
            //if the user clicks on save in Jfilechooser
            if(res == JFileChooser.APPROVE_OPTION){
                File selFile = file.getSelectedFile();
                path.set(selFile.getAbsolutePath());
                //l.setIcon(resize(path));
            }
            imageAdress = path.get();
            if (imageAdress.equals("") == false) {
                image = new Image(imageAdress);
                imageView.setImage(image);
            }
                ArrayList<String> addres = new ArrayList<>();
                addres.add(imageAdress);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(saveImage);
                    ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
                    outputStream.writeObject(addres);
                    fileOutputStream.close();
                    outputStream.close();
                } catch (IOException es) {
                    es.printStackTrace();
                }
        });
        frame.add(jlTitle);
        frame.add(jpButton);
        frame.setVisible(true);
    }
    @FXML
    public void setReceiveImageAddress() {
        this.imageAdress = this.receiveImageAddress.getText();
       /* ArrayList<String> a = new ArrayList<>();
        a.add(imageAdress);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(saveImage);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(a);
            fileOutputStream.close();
            out.close();
            System.out.println("!@#" + a.size());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    @FXML
    public void recieveUserName() {this.userName = this.userNameField.getText();saveImage = new File(userName + "profileeeee.binary");}
    @FXML
    public void recievePassword() {this.password = this.passwordField.getText();}
    @FXML
    public void recieveEmail() {this.email = this.emailFiled.getText();}
    @FXML
    public void recivePhoneNumber() {this.phoneNumber = this.phoneNumberField.getText();}
    @FXML
    public void reciveProfile() {this.image = this.imageView.getImage();}

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
        for(Register accunt : registers) {
            if (accunt.userName.equals(userName) == false) {
                c++;
            }
        }
        if (c == registers.size()) {
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
        for (Register accunt : registers) {
            if (accunt.password.equals(password) == false) {
                c++;
            }
        }
        if (c == registers.size()) {
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
        } else if (phoneNumber.charAt(0) == '0' && phoneNumber.charAt(1) == '9'){
            int counter = 0;
            for (int i = 2; i < 11; i++) {
                if (phoneNumber.charAt(i) >= 48 && phoneNumber.charAt(i) <= 57) {
                    //System.out.println("Not Valid\nTry Again");
                    counter++;
                }
            }
            if (counter == 9) {
                check = true;
                // this.phoneNumberr = phoneNumber;
            }
        }
        return check;
    }

    @FXML
    public void check(Event event) {
        if (handleUserName(this.userName) && handlePassword(this.password) && handleEmail(this.email) && handlePhoneNuber(this.phoneNumber)) {
            if (file.length() != 0) {
                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    ObjectInputStream in = new ObjectInputStream(inputStream);
                    registers = (ArrayList<Register>) in.readObject();
                    inputStream.close();
                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(registers.size());
            registers.add(this);
            System.out.println(registers.size());
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(outputStream);
                out.writeObject(registers);
                outputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            label.setTextFill(Color.GREEN);
            label.setText("succufully");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(registers);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (handleUserName(this.userName) == false) {
            this.label.setTextFill(Color.RED);
            this.label.setText("Invald user name");
            this.userNameField.setText("");
            this.passwordField.setText("");
            this.emailFiled.setText("");
            this.phoneNumberField.setText("");
        } else if (handlePassword(this.password) == false) {
            System.out.println(this.password);
            this.label.setTextFill(Color.RED);
            this.label.setText("Invald password");
            this.userNameField.setText("");
            this.passwordField.setText("");
            this.emailFiled.setText("");
            this.phoneNumberField.setText("");
        } else if (handleEmail(this.email) == false) {
            label.setTextFill(Color.RED);
            label.setText("Invlad email");
            userNameField.setText("");
            passwordField.setText("");
            emailFiled.setText("");
            phoneNumberField.setText("");
        } else if (handlePhoneNuber(this.phoneNumber)) {
            label.setTextFill(Color.RED);
            label.setText("Invalid phonenumber");
            this.userNameField.setText("");
            this.passwordField.setText("");
            this.emailFiled.setText("");
            this.phoneNumberField.setText("");
        }
    }

    @FXML
    public void back(Event event) throws IOException{
        for (Register register : registers) {
            System.out.println(register.getUserName());
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("enterAccunt.fxml"));
        Parent root = loader.load();
        EnterAccunt enterAccunt = loader.getController();
        enterAccunt.reciveAccunts(registers);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {return email;}
    public String getPhoneNumber() {return phoneNumber;}
    public ArrayList<Register> getRegisters() {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            this.registers = (ArrayList<Register>) in.readObject();
            fileInputStream.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return registers;
    }
   // String  s = "E:\\project1";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       file = new File("userssssssst.binary");
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
    public void setPassword(String password) {this.password = password;}
    public void setEmail (String  emainl) {this.email = emainl;}
    public void setPhoneNumber(String  phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setImageAdress(String imageAdress) {this.imageAdress = imageAdress;}

}

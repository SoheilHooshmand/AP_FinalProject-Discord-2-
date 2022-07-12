package com.example.project1;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Accunt {

    private Register register;
    private String status;
    @FXML
    private ImageView imageView = new ImageView();
    @FXML
    private Label userName = new Label();
    @FXML
    private Label password = new Label();
    @FXML
    private Label email = new Label();
    @FXML
    private Label phoneNumber = new Label();
    @FXML
    private TextField changeUSerName = new TextField();
    @FXML
    private TextField changePassword = new TextField();
    @FXML
    private TextField changeEmail = new TextField();
    @FXML
    private TextField changePhoneNumber = new TextField();
    @FXML
    private TextField changeProfile = new TextField();
    @FXML
    private Button applyChangeEmail = new Button();
    @FXML
    private Button applyChangePhonenumber = new Button();
    @FXML
    private Button changeImageAddress = new Button();
    @FXML
    private Button back = new Button();
    private String newPassowrd = "";
    private String newEmail = "";
    private String newPhoneNumber = "";
    private String newImageAdress = "";
    private ArrayList<Register> registers = new ArrayList<>();
    @FXML
    private Button changePassowrd = new Button();
    public void setRegister(Register register) {
        this.register = register;
        this.registers = this.register.getRegisters();
        File file = new File(register.getUserName() + "profileeeee.binary");
        ArrayList<String> a = new ArrayList<>();
        if (file.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                a = (ArrayList<String>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        String addres = "";
        if (a.size() != 0) {
            addres = a.get(0);
        }
        System.out.println(addres);
        //Image image = new Image("E:\\project1\\profile2.jpg");
        if (addres.equals("") == false) {
            Image image = new Image(addres);
            imageView.setImage(image);
        }
        //this.register = register;
        userName.setText("User name: " + register.getUserName());
        password.setText("Passowrd: " + register.getPassword());
        email.setText("Emial: " + register.getEmail());
        phoneNumber.setText("Phonenumber: " +register.getPhoneNumber());
    }
    @FXML
    public void setNewPassowrd() {
        this.newPassowrd = this.changePassword.getText();
    }
    @FXML
    public void setNewEmain() {
        this.newEmail = this.changeEmail.getText();
    }
    @FXML
    public void setNewPhoneNumber() {
        this.newPhoneNumber = changePhoneNumber.getText();
    }
    @FXML
    public void setNewImageAdress() {
        this.newImageAdress = changeProfile.getText();
    }
    @FXML
    public void changePassword() {
        boolean check = false;
        int conter = 0;
        int cont = 0;
        if (newPassowrd.length() >= 8) {
            for (int i = 0; i < newPassowrd.length(); i++) {
                if ((newPassowrd.charAt(i) >= 65 && newPassowrd.charAt(i) <= 90) || (newPassowrd.charAt(i) >= 97 && newPassowrd.charAt(i) <= 122) || (newPassowrd.charAt(i) >= 48 && newPassowrd.charAt(i) <= 57)) {
                    //check = true;
                    // this.passwordd = password;
                    cont++;
                }
            }
        }
        if (cont == newPassowrd.length()) {
            conter++;
        }
        int c = 0;
        for (Register accunt : registers) {
            if (accunt.getPassword().equals(newPassowrd) == false) {
                c++;
            }
        }
        if (c == registers.size()) {
            conter ++;
        }
        if (conter == 2) {
            check = true;
        }
        if (check == true) {
            int a = 0;
           for(int i = 0; i < registers.size(); i++) {
               if (registers.get(i).getPassword().equals(this.register.getPassword())) {
                   a = i;
                   Register register = registers.get(i);
                   register.setPassword(newPassowrd);
                   registers.set(i, register);
               }
           }
           System.out.println(registers.get(a).getPassword());
           try {
               FileOutputStream fileOutputStream = new FileOutputStream("userssssssst.binary");
               ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
               out.writeObject(registers);
               fileOutputStream.close();
               out.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
           try {
               FileOutputStream fileOutputStream = new FileOutputStream("accuntsssssssList.binary");
               ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
               out.writeObject(registers);
               fileOutputStream.close();
               out.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
        }
    }

    public void changeEmail() {
        boolean check = false;
        boolean regerx = false;
        Pattern myEmail = Pattern.compile("@gmail.com");
        //System.out.println("Enter Email\nForm: *********@gmail.com");
        //Scanner in = new Scanner(System.in);
        //String email = in.nextLine();
        Matcher myMatch=myEmail.matcher(newEmail);
        //regex
        if(myMatch.find()&&(newEmail.substring(newEmail.length()-10)).equals("@gmail.com")) {
            //this.emaill = email;
            check = true;
        }
        if (check == true) {
            for (int i = 0 ; i< registers.size(); i++) {
                if (registers.get(i).getEmail().equals(this.register.getEmail())) {
                    Register register = registers.get(i);
                    register.setEmail(newEmail);
                    registers.set(i, register);
                }
            }
            try {
                FileOutputStream fileInputStream = new FileOutputStream("userssssssst.binary");
                ObjectOutputStream outputStream = new ObjectOutputStream(fileInputStream);
                outputStream.writeObject(registers);
                fileInputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream("accuntsssssssList.binary");
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(registers);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void changePhoneNumber () {
        boolean check = false;
        if (newPhoneNumber.equals("")) {
            check = true;
        } else if (newPhoneNumber.charAt(0) == (char) 0 && newPhoneNumber.charAt(1) == (char) 9){
            int counter = 0;
            for (int i = 2; i < 11; i++) {
                if (newPhoneNumber.charAt(i) >= 48 && newPhoneNumber.charAt(i) <= 57) {
                    //System.out.println("Not Valid\nTry Again");
                    counter++;
                }
            }
            if (counter == 11) {
                check = true;
                // this.phoneNumberr = phoneNumber;
            }
        }
        if (check == true) {
            for (int i = 0; i < registers.size(); i++) {
                if (registers.get(i).getPhoneNumber().equals(this.register.getPhoneNumber())) {
                    Register register = registers.get(i);
                    register.setPhoneNumber(newPhoneNumber);
                    registers.set(i, register);
                    break;
                }
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream("userssssssst.binary");
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(registers);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream("accuntsssssssList.binary");
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(registers);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void changeImageAddres() {
        if (newImageAdress.equals("")) {
            ArrayList<String> strings = new ArrayList<>();
            try {
                FileInputStream fileInputStream = new FileInputStream(this.register.getUserName() + "profileeeee.binary");
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                strings = (ArrayList<String>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            strings.clear();
            strings.add(newImageAdress);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(this.register.getUserName() + "profileeeee.binary");
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(strings);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
    public void changeImage(){
        AtomicReference<String> path = new AtomicReference<>("");
        File save = new File(this.register.getUserName() + "profileeeee.binary");
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
            String imageAdress = "";
            imageAdress = path.get();
            if (imageAdress.equals("") == false) {
                Image image = new Image(imageAdress);
                imageView.setImage(image);
                ArrayList<String> addres = new ArrayList<>();
                try {
                    FileInputStream fileInputStream = new FileInputStream(save);
                    ObjectInputStream in = new ObjectInputStream(fileInputStream);
                    addres = (ArrayList<String>) in.readObject();
                    fileInputStream.close();
                    in.close();
                } catch (IOException | ClassNotFoundException ef) {
                    ef.printStackTrace();
                }
                addres.clear();
                addres.add(imageAdress);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(save);
                    ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
                    outputStream.writeObject(addres);
                    fileOutputStream.close();
                    outputStream.close();
                } catch (IOException es) {
                    es.printStackTrace();
                }
            }
        });
        frame.add(jlTitle);
        frame.add(jpButton);
        frame.setVisible(true);
    }

}

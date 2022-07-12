package com.example.project1;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateGroup implements Initializable , Serializable{

    private String gropName = "";
    private Register mainAdmin;
    private static ArrayList<String> namesOfGroups = new ArrayList<>();
    private ArrayList<CreateGroup> groups = new ArrayList<>();
    @FXML
    private transient TextField nameField = new TextField();
    @FXML
    private transient Label label = new Label();
    private transient File file;
    private transient File admin;
    private transient File h;
    @FXML
    public void setGropName() {this.gropName = this.nameField.getText();}
    public void setMainAdmin(Register register) {
        this.mainAdmin = register;
        h = new File(register.getUserName() + "Gruopssss.binary");
        if (h.length() == 0) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(h);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(groups);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(h);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                groups = (ArrayList<CreateGroup>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public String getGropName() {
        return gropName;
    }
    @FXML
    public void create(Event event) {
        int counter = 0;
        for (String s : namesOfGroups) {
            if (s.equals(this.gropName) == false) {
                counter++;
            }
        }
        if (counter == namesOfGroups.size() || namesOfGroups.size() == 0) {
            this.label.setTextFill(Color.GREEN);
            this.label.setText("Group created");
            groups.add(this);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(h);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(groups);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            namesOfGroups.add(this.gropName);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(namesOfGroups);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            File file = new File(mainAdmin.getUserName() + "ShowNamee.binary");
            ArrayList<String> shows = new ArrayList<>();
            if (file.length() == 0) {
                shows.add(this.gropName);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                    out.writeObject(shows);
                    fileOutputStream.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    ObjectInputStream in = new ObjectInputStream(fileInputStream);
                    shows = (ArrayList<String>) in.readObject();
                    fileInputStream.close();
                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                shows.add(this.gropName);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                    out.writeObject(shows);
                    fileOutputStream.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            admin = new File(gropName + ".binary");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(admin);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(mainAdmin);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("userAbilities.fxml"));
                Parent root = loader.load();
                UserAbilities userAbilities = loader.getController();
                userAbilities.recieveRegister(mainAdmin);
                userAbilities.recieveGroupsName(gropName);
                userAbilities.recieveGroup(this.groups);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                System.out.println(e.getCause());
            }

        } else {
            label.setTextFill(Color.RED);
            label.setText("There is  group white this name");
        }
        /*System.out.println("system.out.print");
        int conter = 0;
        for (String s : namesOfGroups) {
            if (s.equals(this.gropName) == false) {
                conter++;
            }
        }
        System.out.println(conter);
        if (conter == this.namesOfGroups.size()) {
            label.setTextFill(Color.GREEN);
            label.setText("Group created");
            namesOfGroups.add(this.gropName);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(namesOfGroups);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("userAbilities.fxml"));
                Parent root = loader.load();
                UserAbilities userAbilities = loader.getController();
                userAbilities.recieveGroups(this);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            label.setTextFill(Color.RED);
            label.setText("There is a group white this name");
        }*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        file = new File("NameOfTheGroup.binary");
        if (file.length() == 0) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(namesOfGroups);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                namesOfGroups = (ArrayList<String>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public Register getMainAdmin() {
        return mainAdmin;
    }

    @FXML
    public void back(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userAbilities.fxml"));
            Parent root = loader.load();
            UserAbilities userAbilities = loader.getController();
            userAbilities.recieveRegister(mainAdmin);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

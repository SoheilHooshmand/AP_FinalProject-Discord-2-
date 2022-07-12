package com.example.project1;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class CreateChannal implements Serializable{
    private Register register;
    private String chanalname = "";
    private String access = "";
    private ArrayList<CreateChannal> channals = new ArrayList<>();
    private File channalss;
    private String groupName;
    private Register mainAdmin;
    @FXML
    private transient Button general = new Button("public");
    @FXML
    private transient Button privat = new Button("private");
    @FXML
    private transient TextField nameField = new TextField();
    @FXML
    private transient Label label = new Label();
    @FXML
    private transient Button back = new Button();
    private ArrayList<String> showNames = new ArrayList<>();
    private File showChannalName;

    public String getChanalname() {return this.chanalname;}
    public void recieveRegister(Register register) {
        this.register = register;
    }
    public void reciveAdmin(Register mainAdmin) { this.mainAdmin = mainAdmin;}
    public void recieveGroupName(String groupName) {
        this.groupName = groupName;
        channalss = new File(groupName + "Channalssss.binary");
        if (channalss.length() == 0) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(channalss);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(channals);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(channalss);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                channals = (ArrayList<CreateChannal>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        showChannalName = new File(groupName + "ShowChannalNames.binay");
    }

    @FXML
    public void recieveName() {
        this.chanalname = this.nameField.getText();
    }
    @FXML
    public void setPublic() {
        this.access = "public";
    }
    @FXML
    public void setPrivate() {
        this.access = "private";
    }
    public String getAccess() {return this.access;}

    @FXML
    public void create() {
        if (this.chanalname.equals("") == false && this.access.equals("") == false) {
            int conter = 0;
            for (CreateChannal createChannal : channals) {
                if (createChannal.chanalname.equals(this.chanalname) == false) {
                    conter++;
                }
            }
            if (conter == channals.size()) {
                channals.add(this);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(channalss);
                    ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                    out.writeObject(channals);
                    fileOutputStream.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                label.setTextFill(Color.GREEN);
                label.setText("channal created.");
            } else {
                label.setTextFill(Color.RED);
                label.setText("there is channal white this name.");
            }
            if (showChannalName.length() == 0) {
                showNames.add(chanalname);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(showChannalName);
                    ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                    out.writeObject(showNames);
                    fileOutputStream.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    FileInputStream fileInputStream = new FileInputStream(showChannalName);
                    ObjectInputStream in = new ObjectInputStream(fileInputStream);
                    showNames = (ArrayList<String>) in.readObject();
                    fileInputStream.close();
                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                showNames.add(this.chanalname);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(showChannalName);
                    ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                    out.writeObject(showNames);
                    fileOutputStream.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            label.setTextFill(Color.RED);
            label.setText("enter channal informtion");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

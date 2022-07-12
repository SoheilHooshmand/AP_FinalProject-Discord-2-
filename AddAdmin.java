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

import java.io.IOException;
import java.util.ArrayList;

public class AddAdmin {

    private Register register;
    private ArrayList<Register> groupUsers = new ArrayList<>();
    private Register newAdmin;
    private String newAdminName = "";
    private String groupName;
    @FXML
    private Button addUSerRolle = new Button();
    boolean addUser = false;
    @FXML
    private Button bockUserRolle = new Button();
    private boolean blockUser = false;
    @FXML
    private Button createChannalRolle = new Button();
    private boolean createChannal = false;
    @FXML
    private Button removeChannlRolle = new Button();
    private boolean removeChannal = false;
    @FXML
    private Button reNameChannalRolle = new Button();
    private boolean reNameChannal = false;
    @FXML
    private Button reNameGroupRolle = new Button();
    private boolean reNameGroup = false;
    @FXML
    private TextField textField = new TextField();
    @FXML
    private Label label = new Label();
    @FXML
    private Button button = new Button();
    @FXML
    private Button button1 = new Button();
    @FXML
    public void addUsersetting () {
        this.addUser = true;
    }
    @FXML
    public void bolockUserSetting() {this.blockUser = true;}
    @FXML
    public void createChannalSetting() {this.createChannal = true;}
    @FXML
    public void deleteChannalSetting() {this.removeChannal = true;}
    @FXML
    public void reNameChannalSetting() {this.reNameChannal = true;}
    @FXML
    public void reNameGroupSetting() {this.reNameGroup = true;}
    public void recieveGroupUsers(ArrayList<Register> groupUsers) {
        this.groupUsers = groupUsers;
    }
    public void recieveRegister(Register register) {
        this.register = register;
    }
    public void ReciveGroupName(String  groupName) {
        this.groupName = groupName;
    }
    @FXML
    public void addUserRolle(){

    }
    @FXML
    public void recieveNewAdminName() {
        this.newAdminName = this.textField.getText();
    }
    @FXML
    public void add(Event event) {
        boolean check = false;
        for (int i = 0; i < groupUsers.size(); i++) {
            System.out.println("$" + groupUsers.get(i).getUserName());
            if (groupUsers.get(i).getUserName().equals(newAdminName)) {
                check = true;
                newAdmin = groupUsers.get(i);
                break;
            }
        }
        if (check == true) {
            label.setTextFill(Color.GREEN);
            label.setText("admin added");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Group.fxml"));
                Parent root = loader.load();
                Group group = loader.getController();
                group.setNameOfGroup(this.groupName);
                group.setRegister(this.register);
                group.updateAdmins(newAdmin);
                if (addUser == true) {
                    group.updateAddUserAdmins(newAdmin);
                }
                if (blockUser == true) {
                    group.updateRemoveUser(newAdmin);
                }
                if (createChannal == true) {
                    group.updateCreateChannal(newAdmin);
                }
                if (removeChannal == true) {
                    group.updateRemoveChannal(newAdmin);
                }
                if (reNameGroup == true) {
                    group.updateRenameGroup(newAdmin);
                }
                if (reNameChannal == true) {
                    group.updateRenameChannal(newAdmin);
                }
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            label.setTextFill(Color.RED);
            label.setText("there is not user white this user name in group");
        }
    }
    @FXML
    public void back(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Group.fxml"));
            Parent root = loader.load();
            Group group = loader.getController();
            //System.out.println("**" + this.groupName);
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

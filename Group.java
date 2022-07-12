package com.example.project1;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Group {

    private String nameOfGroup;
    private Register register;
    private Register mainnAdmin;
    private ArrayList<Register> admins = new ArrayList<>();
    private ArrayList<Register> groupUsers = new ArrayList<>();
    private ArrayList<String> createChannals = new ArrayList<>();
    private ArrayList<CreateChannal> channals = new ArrayList<>();
    private ArrayList<CreateGroup> groups = new ArrayList<>();
    private ArrayList<Register> chanalCreater = new ArrayList<>();
    private ArrayList<Register> deleteChannal = new ArrayList<>();
    private ArrayList<Register> reNameGroup = new ArrayList<>();
    private ArrayList<Register> reNameChannal = new ArrayList<>();
    private ArrayList<Register> deleteUser = new ArrayList<>();
    private ArrayList<Register> addUsers = new ArrayList<>();
    private File adminss;
    private File users;
    private File chanals;
    private File gruops;
    private File createdChannal;
    private File addUsserAdmins;
    private File bolckUserAdminss;
    private File createChannalAdmins;
    private File removeChnnalAdmins;
    private File renameGroupAdmins;
    private File reNameChannalAdmins;
    private String showGroupName;
    private String newName;
    private ArrayList<String> showChannal = new ArrayList<>();
    private File showChannalName;
    private ArrayList<String> showChannalNames = new ArrayList<>();
    @FXML
    Label aboutRename = new Label();
    @FXML
    private Label showw = new Label();
    @FXML
    private Button button = new Button();
    @FXML
    VBox vBox = new VBox();
    @FXML
    private Label label = new Label();
    @FXML
    Button buttonf = new Button();
    @FXML
    private Label adminLable = new Label();

    public String getNameOfGroup() {
        return nameOfGroup;
    }

    @FXML
    private Button addAdminButton = new Button();
    @FXML
    private Button showAdmins = new Button();
    @FXML
    private Label bolck = new Label();
    @FXML
    private Button bolckk = new Button();
    @FXML
    private Button button1 = new Button();
    @FXML
    private TextField changeShowName = new TextField();

    @FXML
    public void setShowGroupName(String showGroupName) {
        this.showGroupName = showGroupName;
        showw.setText(this.showGroupName);
    }

    public void setNewName() {
        this.newName = this.changeShowName.getText();
    }

    public void setNameOfGroup(String nameOfGroup) {
        this.nameOfGroup = nameOfGroup;
        //File a = new File(nameOfGroup + ".binary");
        //System.out.println(a.length());
       /* chanals = new File(nameOfGroup + nameOfGroup + "Channalssss.binary");
        ArrayList<String> channasFirstNames = new ArrayList<>();
        if (chanals.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(chanals);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                channals = (ArrayList<CreateChannal>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < channals.size(); i++) {
                String s = channals.get(i).getChanalname();
                channasFirstNames.add(s);
            }
        }
        showChannalName = new File(nameOfGroup + "Show")*/
        File file = new File(nameOfGroup + "ShowChannalNames.binay");
        if (file.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                showChannalNames = (ArrayList<String>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(nameOfGroup + ".binary");
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            this.mainnAdmin = (Register) in.readObject();
            System.out.println(this.mainnAdmin.getUserName());
            fileInputStream.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        adminss = new File(nameOfGroup + "Admins.binary");
        if (adminss.length() == 0) {
            try {
                admins.add(mainnAdmin);
                FileOutputStream fileOutputStream = new FileOutputStream(adminss);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(admins);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(adminss);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                admins = (ArrayList<Register>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        users = new File(nameOfGroup + "Userss.binary");
        if (users.length() == 0) {
            try {
                groupUsers.add(mainnAdmin);
                FileOutputStream fileOutputStream = new FileOutputStream(users);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(groupUsers);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(users);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                groupUsers = (ArrayList<Register>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            for (Register register : groupUsers) {
                System.out.println(register.getUserName());
            }
        }
       /* chanals = new File(nameOfGroup + "Channalss.binary");
        if (chanals.length() == 0) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(chanals);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(channals);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(chanals);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                channals = (ArrayList<CreateChannal>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException  | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }*/
        addUsserAdmins = new File(nameOfGroup + "AddUserAdmins.binary");
        if (addUsserAdmins.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(addUsserAdmins);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                addUsers = (ArrayList<Register>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            addUsers.add(mainnAdmin);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(addUsserAdmins);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(addUsers);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bolckUserAdminss = new File(nameOfGroup + "BlockUserAdmins.binay");
        if (bolckUserAdminss.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(bolckUserAdminss);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                deleteUser = (ArrayList<Register>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            deleteUser.add(mainnAdmin);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(bolckUserAdminss);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(deleteUser);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        createChannalAdmins = new File(nameOfGroup + "CreateChannalAdmins.binary");
        if (createChannalAdmins.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(createChannalAdmins);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                chanalCreater = (ArrayList<Register>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            chanalCreater.add(mainnAdmin);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createChannalAdmins);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(chanalCreater);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        removeChnnalAdmins = new File(nameOfGroup + "RemoveChannalAdmins.binay");
        if (removeChnnalAdmins.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(removeChnnalAdmins);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                deleteChannal = (ArrayList<Register>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            deleteChannal.add(mainnAdmin);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(removeChnnalAdmins);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(deleteChannal);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        renameGroupAdmins = new File(nameOfGroup + "ReNameGroupAdmins.binry");
        if (renameGroupAdmins.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(renameGroupAdmins);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                reNameGroup = (ArrayList<Register>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            reNameGroup.add(mainnAdmin);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(renameGroupAdmins);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(reNameGroup);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        reNameChannalAdmins = new File(nameOfGroup + "ReNameChnnalAdmins.binary");
        if (reNameChannalAdmins.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(reNameChannalAdmins);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                reNameChannal = (ArrayList<Register>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            reNameChannal.add(mainnAdmin);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(reNameChannalAdmins);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(reNameChannal);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRegister(Register register) {
        this.register = register;
        gruops = new File(register.getUserName() + "Groupssss.binary");
        if (gruops.length() == 0) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(gruops);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(groups);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(gruops);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                groups = (ArrayList<CreateGroup>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void recieveGroups(ArrayList<CreateGroup> groups) {

    }

    /*public void ReciveCreatedChannals(ArrayList<CreateChannal> channals) {
        createdChannal = new File(nameOfGroup + "Channals.binary");
        if (createdChannal.length() == 0) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createdChannal);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(this.channals);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(createdChannal);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                this.channals = (ArrayList<CreateChannal>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }*/
    @FXML
    public void showChanals(Event event) {
        chanals = new File(nameOfGroup + "Channalssss.binary");
        if (chanals.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(chanals);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                this.channals = (ArrayList<CreateChannal>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(channals.size());
        for (int i = 0; i < channals.size(); i++) {
            // Button button = new Button(channals.get(i).getChanalname());
            Button button = new Button(showChannalNames.get(i));
            button.setPrefSize(100, 32);
            vBox.getChildren().add(button);
            int finalI = i;
            boolean checkk = false;
            for (Register register : admins) {
                if (register.getUserName().equals(this.register.getUserName())) {
                    checkk = true;
                    break;
                }
            }
            boolean finalCheckk = checkk;
            int finalI1 = i;
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (channals.get(finalI).getAccess().equals("public") || (channals.get(finalI).getAccess().equals("private") && finalCheckk == true)) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("channal.fxml"));
                            Parent root = loader.load();
                            Channal channal = loader.getController();
                            channal.reciveveChannalName(channals.get(finalI1).getChanalname());
                            channal.setRegister(register);
                            channal.setGroupName(nameOfGroup);
                            channal.setShowing(button.getText());
                            channal.setClinetGroup();
                            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        button.setTextFill(Color.RED);
                        button.setText("You can't enter this channal");
                    }
                }
            });
        }
        /*for (int i = 0; i < createChannals.size(); i++) {
            Button button = new Button(createChannals.get(i));
            button.setPrefSize(105, 34);
            vBox.getChildren().add(button);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("chanal.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Group group = loader.getController();
                    group.setNameOfGroup(button.getText());
                    group.setRegister(register);
                    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            });
        }*/
    }

    public void createChannal(Event event) {
        boolean check = false;
        System.out.println("@" + this.register.getUserName());
        System.out.println(":::" + chanalCreater.size());
        for (Register admin : chanalCreater) {
            System.out.println("#" + admin.getUserName());
            if (admin.getUserName().equals(this.register.getUserName())) {
               check = true;
                break;
            }
        }
        if (check == true) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("createChannal.fxml"));
                Parent root = loader.load();
                CreateChannal createChannal = loader.getController();
                createChannal.recieveRegister(this.register);
                createChannal.recieveGroupName(this.nameOfGroup);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            label.setTextFill(Color.RED);
            label.setText("your are not admin");
        }
    }

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

    public void addUSer(Register register) {
        /* users = new File(nameOfGroup + "Userss.binary");
         if (users.length() == 0) {
             groupUsers.add(register);
             try {
                 FileOutputStream fileOutputStream = new FileOutputStream(users);
                 ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                 out.writeObject(this.groupUsers);
                 fileOutputStream.close();
                 out.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         } else {
             try {
                 FileInputStream fileInputStream = new FileInputStream(users);
                 ObjectInputStream in = new ObjectInputStream(fileInputStream);
                 this.groupUsers = (ArrayList<Register>) in.readObject();
                 fileInputStream.close();
                 in.close();
             } catch (IOException | ClassNotFoundException e) {
                 e.printStackTrace();
             }
             groupUsers.add(register);
         }*/
        groupUsers.add(register);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(users);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(groupUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToAddUser(Event event) {
        boolean check = false;
        for (Register register : addUsers) {
            if (register.getUserName().equals(this.register.getUserName())) {
                check = true;
            }
        }
        if (check == true) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addUserToGroup.fxml"));
                Parent root = loader.load();
                AddUserToGroup addUserToGroup = loader.getController();
                addUserToGroup.recieveRegister(this.register);
                addUserToGroup.setGroupName(this.nameOfGroup);
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
    public void goShowUser(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showGroupUsers.fxml"));
            Parent root = loader.load();
            ShowGroupUsers showGroupUsers = loader.getController();
            showGroupUsers.reciveGroupName(this.nameOfGroup);
            showGroupUsers.recieveRegister(this.register);
            showGroupUsers.reciveUsersList(groupUsers);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateAdmins(Register admin) {
        this.admins.add(admin);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(adminss);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(admins);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateAddUserAdmins(Register register) {
        this.addUsers.add(register);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(addUsserAdmins);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(addUsers);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateRemoveUser(Register register) {
        this.deleteUser.add(register);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(bolckUserAdminss);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(deleteUser);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCreateChannal(Register register) {
        this.chanalCreater.add(register);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(createChannalAdmins);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(chanalCreater);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateRemoveChannal(Register register) {
        deleteChannal.add(register);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(removeChnnalAdmins);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(deleteChannal);
            fileOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateRenameGroup(Register register) {
        reNameGroup.add(register);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(renameGroupAdmins);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(reNameGroup);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateRenameChannal(Register register) {
        reNameChannal.add(register);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(reNameChannalAdmins);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(reNameChannal);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToAddAdmin(Event event) {
        if (register.getUserName().equals(mainnAdmin.getUserName())) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addAdmin.fxml"));
                Parent root = loader.load();
                AddAdmin addAdmin = loader.getController();
                //System.out.println("## " + this.nameOfGroup);
                addAdmin.ReciveGroupName(this.nameOfGroup);
                addAdmin.recieveRegister(this.register);
                addAdmin.recieveGroupUsers(this.groupUsers);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            adminLable.setTextFill(Color.RED);
            adminLable.setText("you are not main admin");
        }
    }

    @FXML
    public void goToAdminsList(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showAdmins.fxml"));
            Parent root = loader.load();
            ShowAdmins showAdmins = loader.getController();
            showAdmins.recieveAdminsList(this.admins);
            showAdmins.recieveRegister(this.register);
            showAdmins.recieveGroupName(this.nameOfGroup);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goBlockUser(Event event) {
        boolean check = false;
        for (Register admin : deleteUser) {
            if (admin.getUserName().equals(this.register.getUserName())) {
                check = true;
                break;
            }
        }
        if (check == true) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("blockUser.fxml"));
                Parent root = loader.load();
                BlockUser blockUser = loader.getController();
                blockUser.recieveGroupName(this.nameOfGroup);
                blockUser.recieveRegister(this.register);
                blockUser.recieveUsers(this.groupUsers);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            bolck.setTextFill(Color.RED);
            bolck.setText("you are not admin");
        }
    }

    public void recieveBlock(Register bolckUser) {
        for (int i = 0; i < groupUsers.size(); i++) {
            if (groupUsers.get(i).getUserName().equals(bolckUser.getUserName())) {
                groupUsers.remove(i);
            }
        }
       /* for (Register bolck : groupUsers) {
            if (bolck.getUserName().equals(bolckUser.getUserName())) {
                groupUsers.remove(bolck);
            }
        }*/
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(users);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(groupUsers);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       File file = new File(bolckUser.getUserName() + "binary");
        ArrayList<String> a = new ArrayList<>();
        int indext = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            a = (ArrayList<String>) in.readObject();
            fileInputStream.close();;
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).equals(this.nameOfGroup)) {
                a.remove(i);
                indext = i;
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(a);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file1 = new File(bolckUser.getUserName() + "ShowNamee.binary");
        ArrayList<String> b = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file1);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            b = (ArrayList<String>) in.readObject();
            fileInputStream.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        b.remove(indext);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(b);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToInviteToGroup(Event event) {
        /*File file = new File(nameOfGroup + "Admins.binary");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            admins = (ArrayList<Register>) in.readObject();
            fileInputStream.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        boolean check = false;
        for (Register register : addUsers) {
            if (register.getUserName().equals(this.register.getUserName())) {
                check = true;
                break;
            }
        }
        if (check == true) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addUserHandling.fxml"));
                Parent root = loader.load();
                AdduserHandling adduserHandling = loader.getController();
                adduserHandling.receiveGroupName(this.nameOfGroup);
                adduserHandling.recieveRegisetr(this.register);
                adduserHandling.recivieShowGroup(this.showGroupName);
                //System.out.println("#@" + showGroupName + "@#");
                adduserHandling.setClint();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeShowGroupName() {
        boolean check = false;
        for (Register register : reNameGroup) {
            if (register.getUserName().equals(this.register.getUserName())) {
                check = true;
            }
        }
        if (check == true) {
            String oldName = this.showGroupName;
            String newName = this.newName;
            System.out.println("$%" + newName);
            File file = new File("NameOfTheGroup.binary");
            ArrayList<String> names = new ArrayList<>();
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                names = (ArrayList<String>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("LKL  "+ names.size());
            if (names.contains(newName) == false) {
                this.showGroupName = newName;
                this.showw.setText(newName);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("userAbilities.fxml"));
                    Parent root = loader.load();
                    UserAbilities userAbilities = loader.getController();
                    userAbilities.recieveRegister(this.register);
                    userAbilities.replaceGroupShowName(oldName, newName, groupUsers);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                aboutRename.setTextFill(Color.RED);
                aboutRename.setText("This group name not valid");
            }
        }
    }

    public void removeChannal(Event event) {
        boolean check = false;
        for (Register register : deleteChannal) {
            if (register.getUserName().equals(this.register.getUserName())) {
                check = true;
            }
        }
        if (check == true) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("removeChannal.fxml"));
                Parent root = loader.load();
                RemoveChannal removeChannal = loader.getController();
                removeChannal.receiveGroupName(this.nameOfGroup);
                removeChannal.recieveRegister(this.register);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


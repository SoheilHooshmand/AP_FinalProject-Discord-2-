package com.example.project1;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserAbilities implements Initializable {

    private Register register;
    @FXML
    private transient VBox vBox = new VBox();
    private ArrayList<String> groupsName = new ArrayList<>();
    private ArrayList<Register> friends = new ArrayList<>();
    private transient ArrayList<Button> groupsButton = new ArrayList<>();
    private ArrayList<CreateGroup> groups = new ArrayList<>();
    private ArrayList<String> showGroupNames = new ArrayList<>();
    private ArrayList<Register> blocks = new ArrayList<>();
    @FXML
    private Button showBlockss = new Button();
    private File file;
    private File friednsFile;
    private File groop;
    private File showNames;
    private File blockPersons;
    @FXML
    private Button goToAccunt = new Button();
    @FXML
    private Button friend = new Button();
    @FXML
    private Button personalMessage = new Button();
    @FXML
    private Button showFriends = new Button();
    @FXML
    private Button block = new Button();
   // private ArrayList<CreateGroup> groups = new ArrayList<>();
    @FXML
    public void setGroupsButton() {
        for (int i = 0 ; i < groupsName.size() ; i++)  {
            Button button = new Button(showGroupNames.get(i));
            button.setPrefSize(105, 34);
            vBox.getChildren().add(button);
            int finalI = i;
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Group.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Group group = loader.getController();
                    group.setNameOfGroup(groupsName.get(finalI));
                    group.setShowGroupName(button.getText());
                    /*try {
                        FileInputStream fileInputStream = new FileInputStream(register.getUserName() + "Groupsss.binary");
                        ObjectInputStream in = new ObjectInputStream(fileInputStream);
                        groups = (ArrayList<CreateGroup>) in.readObject();
                        fileInputStream.close();
                        in.close();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Register admin = null;
                    for (CreateGroup createGroup : groups) {
                        if (createGroup.getGropName().equals(button.getText())){
                            admin = createGroup.getMainAdmin();
                        }
                    }
                    group.setMainAdmin(admin);*/
                    group.setRegister(register);
                    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            });
        }
    }


    public void recieveRegister(Register register) {
        //System.out.println(register.getUserName());
        this.register = register;
        /*groop = new File(register.getUserName() + "Groups.binary");
        if (groop.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(groop);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                groups = (ArrayList<CreateGroup>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }*/
        file = new File(register.getUserName() + "binary");
        if (file.length() == 0 ) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(groupsName);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                groupsName = (ArrayList<String>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        showNames = new File(register.getUserName() + "ShowNamee.binary");
        if (showNames.length() == 0 && groupsName.size() !=0) {
           showGroupNames = groupsName;
           System.out.println("#" + groupsName.size());
           System.out.println("#" + showGroupNames.size());
           try {
               FileOutputStream fileOutputStream = new FileOutputStream(showNames);
               ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
               out.writeObject(showGroupNames);
               fileOutputStream.close();
               out.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
        } else if (showNames.length() != 0){
            System.out.println("#" + showNames.length());
            try {
                FileInputStream fileInputStream = new FileInputStream(showNames);
                ObjectInputStream in  = new ObjectInputStream(fileInputStream);
                showGroupNames = (ArrayList<String>) in.readObject();
                System.out.println(showGroupNames.size());
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
       for (String  s : groupsName) {
           System.out.println(s);
       }
       blockPersons = new File(register.getUserName() + "Blocksss.binary");
       if (blockPersons.length() == 0) {
           try {
               FileOutputStream fileOutputStream = new FileOutputStream(blockPersons);
               ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
               out.writeObject(blocks);
               fileOutputStream.close();
               out.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       } else {
           try {
               FileInputStream fileInputStream = new FileInputStream(blockPersons);
               ObjectInputStream in = new ObjectInputStream(fileInputStream);
               blocks = (ArrayList<Register>) in.readObject();
               fileInputStream.close();
               in.close();
           } catch (IOException | ClassNotFoundException e) {
               e.printStackTrace();
           }
       }
    }


    public void recieveGroup(ArrayList<CreateGroup> groups) {
        this.groups = groups;
        groop = new File(register.getUserName() + "Groupssss.binary");
        if (groop.length() == 0) {
            try {
               FileOutputStream fileOutputStream = new FileOutputStream(groop);
               ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
               out.writeObject(this.groups);
               fileOutputStream.close();
               out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(groups.size());
    }
    public void replaceGroupShowName(String oldName, String newName, ArrayList<Register> groupUSers) {
       /* showNames = new File(register.getUserName() + "ShowNamee.binary");
        try {
            FileInputStream fileInputStream = new FileInputStream(showNames);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            showGroupNames = (ArrayList<String>) in.readObject();
            fileInputStream.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("&*" + showGroupNames.size());
        int indext = 0;
        for (int i = 0; i < showGroupNames.size(); i++) {
            if (showGroupNames.get(i) != null && showGroupNames.get(i).equals(oldName)) {
               indext = i;
               break;
            }
            System.out.println(showGroupNames.get(i));
        }
        System.out.println("!@#" + indext);
        showGroupNames.set(indext, newName);
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(showNames);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(showGroupNames);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        for (int i = 0; i < groupUSers.size(); i++) {
            File file = new File(groupUSers.get(i).getUserName() + "ShowNamee.binary");
            ArrayList<String > a = new ArrayList<>();
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                a = (ArrayList<String>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            int indextt = 0;
            for (int j = 0; j < a.size(); j++) {
                if (a.get(j).equals(oldName)) {
                    indextt = j;
                }
            }
            a.set(indextt, newName);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(a);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void recieveGroupsName(String createGroup) {
        file = new File(register.getUserName() + "binary");
        if (file.length() == 0) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(groupsName);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (file.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                groupsName = (ArrayList<String>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(groupsName.size());
        groupsName.add(createGroup);
        System.out.println(groupsName.size());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(groupsName);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(register.getUserName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*file = new File(register.getUserName() + ".binary");
        if (file.length() == 0) {
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(outputStream);
                out.writeObject(groups);
                outputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (file.length() != 0) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    ObjectInputStream in = new ObjectInputStream(fileInputStream);
                    groups = (ArrayList<CreateGroup>) in.readObject();
                    fileInputStream.close();
                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }
    @FXML
    public void createGruop(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("createGroup.fxml"));
            Parent root = loader.load();
            CreateGroup createGroup = loader.getController();
            createGroup.setMainAdmin(this.register);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void back(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("enterAccunt.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addGroup(String groupName, String showGroup) {
       /* file = new File(register.getUserName() + "binary");
        if (file.length() == 0) {
            try {
                this.groupsName.add(groupName);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(groupsName);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }  else {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                this.groupsName = (ArrayList<String>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            this.groupsName.add(groupName);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(this.groupsName);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        groupsName.add(groupName);
        showGroupNames.add(showGroup);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(this.groupsName);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(showNames);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(showGroupNames);
            out.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void personalChat(Event event) {
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
    @FXML
    public void friend(Event event) {
        friednsFile = new File(register.getUserName() + "Friendsss.binary");
        if (friednsFile.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(friednsFile);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                friends = (ArrayList<Register>) in.readObject();
                fileInputStream.close();
                in.close();
                System.out.println(friends.get(0).getUserName());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("empty");
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("friendHandling.fxml"));
            Parent root = loader.load();
            FriendsHandling friendsHandling = loader.getController();
            //System.out.println("!@" + register.getUserName());
            friendsHandling.receiveRegister(this.register);
            friendsHandling.setClint();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showFriends(Event event) {
        friednsFile = new File(register.getUserName() + "Friendsss.binary");
        if (friednsFile.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(friednsFile);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                friends = (ArrayList<Register>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showFriends.fxml"));
            Parent root = loader.load();
            ShowFriend showFriend = loader.getController();
            showFriend.receiveRegster(this.register);
            showFriend.reciveFriends(this.friends);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }

    }
    public void addFriend(Register friend) {
        friednsFile = new File(register.getUserName() + "Friendsss.binary");
        if (friednsFile.length() == 0) {
            friends.add(friend);
            try {
                System.out.println(friends.get(0).getUserName());
                FileOutputStream fileOutputStream = new FileOutputStream(friednsFile);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(friends);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(friednsFile);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                friends = (ArrayList<Register>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException |  ClassNotFoundException e) {
                e.printStackTrace();
            }
            friends.add(friend);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(friednsFile);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(friends);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void showGroupRequest(Event event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showGroupRequestes.fxml"));
            Parent root = fxmlLoader.load();
            ShowGroupRequestes showGroupRequestes = fxmlLoader.getController();
            showGroupRequestes.receiveRegister(this.register);
            showGroupRequestes.ad();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void goToBlock(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("blockUserFromPV.fxml"));
            Parent root = loader.load();
            BlockUserFromPV blockUserFromPV = loader.getController();
            blockUserFromPV.setRegister(this.register);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void receiveBlock(Register register) {
        blocks.add(register);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(blockPersons);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(blocks);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void showBlocklist(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showBlocklList.fxml"));
            Parent root = loader.load();
            ShowBlockList showBlockList = loader.getController();
            showBlockList.setRegister(this.register);
            showBlockList.setBlocks(this.blocks);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void goToAccunt(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("accunt.fxml"));
            Parent root = loader.load();
            Accunt accunt = loader.getController();
            accunt.setRegister(this.register);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

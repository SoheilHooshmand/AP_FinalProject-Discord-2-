package com.example.project1;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String nameOfGroup = "";
    private String nameOfClient = "";
    private String channalName = "";
    private String guest = "";
    private Register register;
    private String friendOrGroup = "";
    private String groupName = "";
    public Client(Socket socket) {
        this.socket = socket;
        try {
            out = new ObjectOutputStream(this.socket.getOutputStream());
            in = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setGroupName(String groupName) {this.groupName = groupName;}
    public void setChannalName(String channalName) {this.channalName = channalName;}
    public void setRegister(Register register) {this.register = register;}
    public void setNameOfGroup(String nameOfGroup) {
        this.nameOfGroup = nameOfGroup;
    }
    public void setNameOfClient(String nameOfClient) {
        this.nameOfClient =  nameOfClient;
    }
    public void setGuest(String guest) {this.guest = guest;}
    public void setFriendOrGroup(String friendOrGroup) {this.friendOrGroup = friendOrGroup;}
    public void close() {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sentMessage(Message message) {
       // System.out.println("@#$");
       // System.out.println("$#" + message.getAccess());
        if (message.getAccess().equals("public")) {
            try {
                //out.writeObject(nameOfGroup);
                //out.writeObject(nameOfClient);
                out.writeObject(null);
                out.writeObject(null);
                out.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (message.getAccess().equals("private")) {
            try {
               // out.writeObject(null);
               // out.writeObject(nameOfClient);
                out.writeObject(message.getGuess());
                out.writeObject(message.getHost());
                out.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (message.getAccess().equals("friend")) {
            try {
               // out.writeObject(null);
               // out.writeObject(nameOfClient);
                out.writeObject(message.getGuess());
                out.writeObject(null);
                out.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (message.getAccess().equals("group")) {
            try {
                out.writeObject(message.getGuess());
                out.writeObject(null);
                out.writeObject(message);
                System.out.println("sented");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void intialize() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(this.socket.getOutputStream());
            outputStream.writeObject(nameOfGroup);
            outputStream.writeObject(nameOfClient);
            outputStream.writeObject(channalName);
            outputStream.writeObject(this.guest);
            outputStream.writeObject(friendOrGroup);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void cover() {
        Message message = new Message("", "", "");
        try {
            ObjectOutputStream out = new ObjectOutputStream(this.socket.getOutputStream());
            out.writeObject("");
            out.writeObject("");
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listening(VBox vBox) {
       new Thread(new Runnable() {
           @Override
           public void run() {
               while (socket.isConnected()) {
                   try {
                       Message message = (Message) in.readObject();
                       if (message.getAccess().equals("public") && message.getNameOfFile().equals("")) {
                           Channal.addAbale(vBox, message);
                       } else if (message.getAccess().equals("private") && message.getNameOfFile().equals("")) {
                           PersonalChat.addAbale(vBox, message);
                           //PersonalChat.addAbale(vBox, message);
                       } else if (message.getAccess().equals("friend")) {
                          // System.out.println("!@#$" + message.getAccess());
                          /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("friendHandling.fxml"));
                           Parent root = fxmlLoader.load();
                           FriendsHandling friendsHandling = fxmlLoader.getController();*/
                          /* try {
                               FXMLLoader loader = new FXMLLoader(getClass().getResource("friendHandling.fxml"));
                               Parent root = loader.load();
                               FriendsHandling friendsHandling = loader.getController();
                               friendsHandling.receiveRegister(register);
                               friendsHandling.addAbale(vBox, message);
                               /*File file = new File(register.getUserName() + "receiverRequest.binary");
                               ArrayList<String> messages = new ArrayList<>();
                               if (file.length() != 0) {
                                   try {
                                       FileInputStream fileInputStream = new FileInputStream(file);
                                       ObjectInputStream in = new ObjectInputStream(fileInputStream);
                                       messages = (ArrayList<String>) in.readObject();
                                       fileInputStream.close();
                                       in.close();
                                   } catch (IOException e) {
                                       e.printStackTrace();
                                   }
                               }
                               messages.add(message.getAuther());
                               try {
                                   FileOutputStream fileOutputStream = new FileOutputStream(file);
                                   ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                                   out.writeObject(messages);
                                   fileOutputStream.close();
                                   out.close();
                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                           } catch (IOException e) {
                               e.printStackTrace();
                           }*/
                           ArrayList<String> strings = new ArrayList<>();
                           File file = new File(register.getUserName() + "receiverRequest.binary");
                           if (file.length() != 0) {
                               try {
                                   FileInputStream fileInputStream = new FileInputStream(file);
                                   ObjectInputStream in = new ObjectInputStream(fileInputStream);
                                   strings = (ArrayList<String>) in.readObject();
                                   fileInputStream.close();
                                   in.close();
                               } catch (IOException | ClassNotFoundException e) {
                                   e.printStackTrace();
                               }
                           }
                           strings.add(message.getAuther());
                           try {
                               FileOutputStream fileOutputStream = new FileOutputStream(file);
                               ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                               out.writeObject(strings);
                               fileOutputStream.close();
                               out.close();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       } else if (message.getAccess().equals("group")) {
                           System.out.println("++++++"+ message.getShowGroup());
                          File file = new File(message.getGuess() + "JoinGroup.binay");
                          File file1 = new File(message.getGuess() + "ShowGroupJoinGroup.binary");
                          ArrayList<String> strings = new ArrayList<>();
                          ArrayList<String> strings1 = new ArrayList<>();
                          if (file1.length() != 0) {
                              try {
                                  FileInputStream fileInputStream = new FileInputStream(file1);
                                  ObjectInputStream in = new ObjectInputStream(fileInputStream);
                                  strings1 = (ArrayList<String>) in.readObject();
                                  fileInputStream.close();
                                  in.close();
                              } catch (IOException e) {
                                  e.printStackTrace();
                              }
                          }
                          strings1.add(message.getShowGroup());
                          try {
                              FileOutputStream fileOutputStream = new FileOutputStream(file1);
                              ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                              out.writeObject(strings1);
                              fileOutputStream.close();
                              out.close();
                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                          if (file.length() != 0) {
                              try {
                                  FileInputStream fileInputStream = new FileInputStream(file);
                                  ObjectInputStream in = new ObjectInputStream(fileInputStream);
                                  strings = (ArrayList<String>) in.readObject();
                                  fileInputStream.close();
                                  in.close();
                              } catch (IOException | ClassNotFoundException e) {
                                  e.printStackTrace();
                              }
                          }
                          strings.add(message.getGroupName());
                          System.out.println(strings.size());
                          try {
                              FileOutputStream fileOutputStream = new FileOutputStream(file);
                              ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                              out.writeObject(strings);
                              fileOutputStream.close();
                              out.close();
                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                       } else if (message.getAccess().equals("public") && message.getNameOfFile().equals("") == false) {
                           /*File file = new File(channalName + "files.binary");
                           ArrayList<Message> messages = new ArrayList<>();
                           if (file.length() != 0) {
                               try {
                                   FileInputStream fileInputStream = new FileInputStream(file);
                                   ObjectInputStream in = new ObjectInputStream(fileInputStream);
                                   messages = (ArrayList<Message>) in.readObject();
                                   fileInputStream.close();
                                   in.close();
                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                           }
                           messages.add(message);
                           try {
                               FileOutputStream fileOutputStream = new FileOutputStream(file);
                               ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                               out.writeObject(messages);
                               fileOutputStream.close();
                               out.close();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }*/
                           System.out.println("fiel file file file file");
                           Channal.addFile(vBox, message);
                           System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                       } else if (message.getAccess().equals("private") && message.getNameOfFile().equals("") == false) {
                           System.out.println("#$" + "private file");
                           PersonalChat.addFile(vBox, message);
                       }

                   } catch (IOException | ClassNotFoundException e) {
                       e.printStackTrace();
                   }
               }
           }
       }).start();
    }
}

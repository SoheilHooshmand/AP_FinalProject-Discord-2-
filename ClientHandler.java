package com.example.project1;

import java.io.*;
import java.net.Socket;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.HashSet;

public class ClientHandler implements Runnable, Serializable{

    private transient Socket socket;
    private transient ObjectOutputStream out;
    private transient ObjectInputStream in;
    private transient ObjectInputStream inputStream;
    private String groupName = "";
    private String name = "";
    private String host = "";
    private String guess = "";
    private String channalName = "";
    private String guset = "";
    private String friendOrGroup = "";
    private String fileOrMessage = "";
   // private File file = new File("allClientsssss.ser");
    private static HashSet<ClientHandler> clientHandlers = new HashSet<>();
    //private String groupName2 = "";

    public ClientHandler(Socket socket) {
        System.out.println("vasl shod");
        this.socket = socket;
        try {
            out = new ObjectOutputStream(this.socket.getOutputStream());
            in = new ObjectInputStream(this.socket.getInputStream());
            inputStream = new ObjectInputStream(this.socket.getInputStream());
            this.groupName = (String) in.readObject();
            this.name = (String) in.readObject();
            this.channalName = (String) in.readObject();
            this.guess = (String) in.readObject();
            this.friendOrGroup = (String) in.readObject();
            System.out.println(name + "*");
            System.out.println(friendOrGroup + "*");
          //  System.out.println("#@"  +  friendOrGroup + "%^");
           // System.out.println("#@" + groupName + "%$");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientHandlers.add(this);
        if (groupName.equals("") == false) {
            File file = new File(channalName + "Public.binary");
            File file1 = new File(channalName + "Files.binary");
            if (file.length() != 0) {
                ArrayList<Message> messages = new ArrayList<>();
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    ObjectInputStream in = new ObjectInputStream(fileInputStream);
                    messages = (ArrayList<Message>) in.readObject();
                    fileInputStream.close();
                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    //System.out.println("file not found");
                    e.printStackTrace();
                }
                for (Message message : messages) {
                    try {
                        out.writeObject(message);
                        System.out.println(message.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                        //System.out.println("found exception");
                    }
                }
            }
            if (file1.length() != 0) {
                ArrayList<Message> files = new ArrayList<>();
                try {
                    FileInputStream fileInputStream = new FileInputStream(file1);
                    ObjectInputStream in = new ObjectInputStream(fileInputStream);
                    files = (ArrayList<Message>) in.readObject();
                    fileInputStream.close();
                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                for (Message message : files) {
                    try {
                        out.writeObject(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (groupName.equals("") && friendOrGroup.equals("")){
            File file = new File(name + guess + "Private.binary");
            File file1 = new File(guess + name + "Private.binary");
            File file2 = new File(name + guess + "Files.binary");
            File file3 = new File(guess + name + "Files.binary");
            if (file.length() != 0) {
                ArrayList<Message> messages = new ArrayList<>();
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    ObjectInputStream in = new ObjectInputStream(fileInputStream);
                    messages = (ArrayList<Message>) in.readObject();
                    fileInputStream.close();
                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    //System.out.println("file not found");
                    e.printStackTrace();
                }
                //System.out.println("1@" + messages.size());
                for (Message message : messages) {
                    try {
                        out.writeObject(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                        //System.out.println("found exception");
                    }
                }
            }
            if (file1.length() != 0) {
                ArrayList<Message> messages = new ArrayList<>();
                try {
                    FileInputStream fileInputStream = new FileInputStream(file1);
                    ObjectInputStream in = new ObjectInputStream(fileInputStream);
                    messages = (ArrayList<Message>) in.readObject();
                    fileInputStream.close();
                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    //System.out.println("file not found");
                    e.printStackTrace();
                }
                //System.out.println("2@" + messages.size());
                for (Message message : messages) {
                    try {
                        out.writeObject(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                        //System.out.println("found exception");
                    }
                }
            }
            if (file2.length() != 0) {
                ArrayList<Message> files = new ArrayList<>();
                try {
                    FileInputStream fileInputStream = new FileInputStream(file2);
                    ObjectInputStream in = new ObjectInputStream(fileInputStream);
                    files = (ArrayList<Message>) in.readObject();
                    fileInputStream.close();
                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                for (Message message : files) {
                    try {
                        out.writeObject(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (file3.length() != 0) {
                ArrayList<Message> files = new ArrayList<>();
                try {
                    FileInputStream fileInputStream = new FileInputStream(file3);
                    ObjectInputStream in = new ObjectInputStream(fileInputStream);
                    files = (ArrayList<Message>) in.readObject();
                    fileInputStream.close();
                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                for (Message message : files) {
                    try {
                        out.writeObject(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (groupName.equals("") && friendOrGroup.equals("friend")) {
            System.out.println("@#");
            File file = new File( name + "Friend.binary");
           // File file11 = new File(guess + name + "Friend.binary");
            ArrayList<Message> messages = new ArrayList<>();
            if (file.length() != 0) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    ObjectInputStream in = new ObjectInputStream(fileInputStream);
                    messages = (ArrayList<Message>) in.readObject();
                    fileInputStream.close();
                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("@1" + messages.size());
                for (Message message : messages) {
                    try {
                        out.writeObject(message);
                        System.out.println(message.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("1@empty");
            }
            /*if (file11.length() != 0) {
                ArrayList<Message>messages1 = new ArrayList<>();
                try {
                    FileInputStream fileInputStream = new FileInputStream(file11);
                    ObjectInputStream in = new ObjectInputStream(fileInputStream);
                    messages1 = (ArrayList<Message>) in.readObject();
                    fileInputStream.close();
                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("@2" + messages.size());
                for (Message message : messages1) {
                    try {
                        out.writeObject(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("2@empty");
            }*/
        } else if (groupName.equals("") && friendOrGroup.equals("group")) {
            System.out.println("!@ " + friendOrGroup);
           File file = new File(name + "Group.binary");
           ArrayList<Message> messages = new ArrayList<>();
           if (file.length() != 0) {
               try {
                   FileInputStream fileInputStream = new FileInputStream(file);
                   ObjectInputStream in = new ObjectInputStream(fileInputStream);
                   messages = (ArrayList<Message>) in.readObject();
                   fileInputStream.close();
                   in.close();
               } catch (IOException | ClassNotFoundException e) {
                   e.printStackTrace();
               }
               for (Message message : messages) {
                   try {
                       out.writeObject(message);
                       System.out.println(message.toString());
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
        } else if(channalName.equals("") == false && fileOrMessage.equals("file")) {
            //sentFileToChannal();
        }
    }

    public void run() {
        while (this.socket.isConnected()) {
            //if (fileOrMessage.equals("message")) {
                try {

                    this.guess = (String) inputStream.readObject();
                    this.host = (String) inputStream.readObject();
                    Message message = (Message) inputStream.readObject();
                    System.out.println("payam daryaft shod");
                    //Message message = new Message("fsdaf", "asdfghjk" ,"public");
                    System.out.println("!@1 " + message.getAccess());
                    if (message.getAccess().equals("public") && message.getNameOfFile().equals("")) {
                        sentPublicMessage(message);
                    } else if (message.getAccess().equals("private") && message.getNameOfFile().equals("")) {
                        sentPrivateMessage(message);
                    } else if (message.getAccess().equals("friend")) {
                        sentFriendReguest(message);
                        //System.out.println("$friend");
                    } else if (message.getAccess().equals("group")) {
                        System.out.println("friend");
                        sntdGroupRequest(message);
                    } else if (message.getAccess().equals("public") && message.getNameOfFile().equals("") == false) {
                        System.out.println("@###" + message.getNameOfFile());
                        sentFileToChannal(message);
                    } else if (message.getAccess().equals("private") && message.getNameOfFile().equals("") == false) {
                        sentFileToPV(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            /*} else {
                MyFile myFile;
                try {
                    this.guess = (String) inputStream.readObject();
                    this.host = (String) inputStream.readObject();
                    myFile = (MyFile) in.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }*/
        }
    }

    public void sentPublicMessage(Message message) {
        File file = new File( channalName + "Public.binary");
        ArrayList<Message> messages = new ArrayList<>();
        if (file.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                messages = (ArrayList<Message>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException |  ClassNotFoundException e) {
                e.printStackTrace();
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
            }
        } else {
            messages.add(message);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(messages);
                fileOutputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.groupName.equals(message.getGroupName()) && clientHandler.channalName.equals(message.getChannalName()) && !(clientHandler.name.equals(message.getAuther()))) {
                try {
                    clientHandler.out.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    //System.out.println("not found exception");
                }
            }
            //System.out.println(clientHandler.groupName + "%%%");
        }
    }
    public void sentPrivateMessage(Message message) {
        File file = new File(name + guess + "Private.binary");
        ArrayList<Message> messages = new ArrayList<>();
        if (file.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                messages = (ArrayList<Message>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
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
        }
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.name.equals(message.getGuess())) {
                try {
                    System.out.println(message.toString());
                    clientHandler.out.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sentFriendReguest(Message message) {
        File file = new File( guess + "Friend.binary");
        ArrayList<Message> messages = new ArrayList<>();
        if (file.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                messages = (ArrayList<Message>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e){
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
        }
        for (ClientHandler clientHandler : clientHandlers){
            if (clientHandler.name.equals(message.getGuess())) {
                try {
                    clientHandler.out.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void sntdGroupRequest(Message message) {
        File file = new File(guess + "Group.binary");
        ArrayList<Message> messages = new ArrayList<>();
        if (file.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                messages = (ArrayList<Message>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
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
        }
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.name.equals(message.getGuess())) {
                try {
                    clientHandler.out.writeObject(message);
                    System.out.println(message.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void sentFileToChannal(Message message) {
        File file = new File(channalName + "Files.binary");
        ArrayList<Message> files = new ArrayList<>();
        if (file.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                files = (ArrayList<Message>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        files.add(message);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(files);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("###" + message.getNameOfFile() + "###");
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.groupName.equals(message.getGroupName()) && clientHandler.channalName.equals(message.getChannalName()) && !(clientHandler.name.equals(message.getAuther()))) {
                try {
                    clientHandler.out.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    //System.out.println("not found exception");
                }
            }
            //System.out.println(clientHandler.groupName + "%%%");
        }
    }
    public void sentFileToPV (Message message) {
        File file = new File(name + guess + "Files.binary");
        ArrayList<Message> files = new ArrayList<>();
        if (file.length() != 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                files = (ArrayList<Message>) in.readObject();
                fileInputStream.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        files.add(message);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(files);
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ClientHandler clientHandler : clientHandlers) {
            System.out.println(clientHandler.name);
            if (clientHandler.name.equals(message.getGuess())) {
                try {
                   // System.out.println(message.toString());
                    clientHandler.out.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

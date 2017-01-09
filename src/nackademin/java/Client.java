package nackademin.java;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
                            //check sync
public class Client{
    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader readServerData;
    private List<Contact> serverContacts;

    public void serverConnect(){
        setupClient();
        setupCommunication();
        new Thread(()->{
        requestServerData();
        getServerData();
        storeCentralContacts();
         }).start();
    }

    private void setupClient(){
        try{
            clientSocket = new Socket("localhost", 61616);
            clientSocket.setSoTimeout(10000);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void setupCommunication(){
        try {
            writer = new PrintWriter(clientSocket.getOutputStream());
            readServerData = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void requestServerData(){
        writer.println("getall");
        writer.flush();
    }

    private String getServerData(){
        String dbContacts = null;
        try {
            InputStreamReader binaryToString = new InputStreamReader(clientSocket.getInputStream());
            readServerData = new BufferedReader(binaryToString);
            for (String tempString = readServerData.readLine(); !tempString.isEmpty(); tempString = readServerData.readLine()){
            if (tempString.equals("")) {
                break;
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbContacts;
    }

    private void disconnect(){
        writer.println("exit");
        writer.flush();
        closeCommunication();
    }

    private void closeCommunication(){
        try{
            writer.close();
            readServerData.close();
            clientSocket.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public List<Contact> storeCentralContacts(){        //Allt på en sträng
        String dbContact = getServerData();
        //dbContact.replace(","," ");
        String[] stringSplitter = dbContact.split(" ");
        Contact contact = new Contact(stringSplitter[0],stringSplitter[1],stringSplitter[2]);
        serverContacts = new ArrayList<>();
        serverContacts.add(contact);
        disconnect();
        return serverContacts;
    }
}

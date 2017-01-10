package nackademin.java;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client{
    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;
    private String tempStr;


    public void connectToServer(){
        try {
            clientSocket = new Socket("localhost", 61616);
            clientSocket.setSoTimeout(10000);
            setupCommunication();
            storeData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setupCommunication(){
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> storeData(){
        String serverData = null;
        String[] stringSplitter;
        List<Contact> centralContacts = new ArrayList<>();
        sendRequest();
        try {
            while ((tempStr = reader.readLine()) != null) {
                serverData += tempStr;
                stringSplitter = serverData.split(" ");
                Contact contact = new Contact(stringSplitter[0], stringSplitter[1], stringSplitter[2], stringSplitter[3]);
                centralContacts.add(contact);
                }
            } catch(IOException e){
                e.printStackTrace();
            }
            return centralContacts;
    }

    private void sendRequest(){
        writer.println("getall");
        writer.flush();
    }

    public void getRemoteRegistryContent(){

    }
}

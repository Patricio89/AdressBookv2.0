package nackademin.java;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client{
    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;

    private ArrayList<Contact> centralContacts = new ArrayList<>();

    public void connectToServer(){
        try {
            clientSocket = new Socket("localhost", 61616);
            clientSocket.setSoTimeout(10000);
            setupCommunication();
            storeData();
        } catch (ConnectException ex){
            System.out.println("Connection refused, server might be down.");
            ex.printStackTrace();
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

    public void storeData(){
        String serverData;
        String[] stringSplitter;
        sendRequest();
        try {
            for (serverData = reader.readLine(); serverData != null; serverData = reader.readLine()){
                if (serverData.equals("")){
                    break;
                }
                //System.out.println(serverData);
                stringSplitter = serverData.split(" ");

                Contact contact = new Contact(stringSplitter[0], stringSplitter[1], stringSplitter[2], stringSplitter[3]);
                             centralContacts.add(contact);
            }
            closeConnection();

            } catch(IOException e){
                e.printStackTrace();
            }
    }

    private void sendRequest(){
        writer.println("getall");
        writer.flush();
    }

    public ArrayList<Contact> getCentralContacts() {
        return centralContacts;
    }

    public void closeConnection(){
        writer.println("exit");
        writer.flush();

        writer.close();
        try {
            reader.close();
            clientSocket.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}


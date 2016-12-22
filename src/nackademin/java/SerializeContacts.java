package nackademin.java;


import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerializeContacts {
    private static final Logger logger = Logger.getLogger(SerializeContacts.class.getName());

    public void save(ArrayList<Contact> register){
        try{
            FileOutputStream dataToFile = new FileOutputStream("Contacts.ser");
            ObjectOutputStream sendObject = new ObjectOutputStream(dataToFile);
            sendObject.writeObject(register);
            sendObject.close();
            dataToFile.close();
        }catch(IOException ex){
            logger.log(Level.SEVERE,"IOException caught during serialization", ex);
        }
    }

    public ArrayList<Contact> load() {
        ArrayList<Contact> fileRegister = null;
        try {
            FileInputStream getDataFromFile = new FileInputStream("Contacts.ser");
            ObjectInputStream getObject = new ObjectInputStream(getDataFromFile);
            fileRegister = (ArrayList<Contact>)getObject.readObject();
            getObject.close();
            getDataFromFile.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE,"Error occured during load from file", ex);
        }catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE,"Could not find connected class to load objects", e);
        }
        return fileRegister;

    }
}

package nackademin.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Register implements Serializable{

    SerializeContacts fileManager = new SerializeContacts();
    Client client = new Client();
    ArrayList<Contact> contactList = new ArrayList<>();
    ArrayList<Contact> serverContactList = new ArrayList<>();

    Contact contact;
    TerminalMessage messenger = new TerminalMessage();

    public void fileContactToContactList(){
        contactList.addAll(fileManager.load());
    }

    public void getServerList(ArrayList<Contact> serverContactList){

    }

    public void add(String name, String surName, String email){         //check
        contactList.add(contact = new Contact(name,surName,email));
        messenger.addMessage();
    }

    public void delete(String removeByID){                                  //Check
    //    for (Iterator iterate = contactList.iterator(); iterate.hasNext();){
        for (int i = contactList.size()-1;i > -1; i--){
            if (removeByID.matches(contactList.get(i).getId().toString())){
                contactList.remove(contactList.get(i));
                break;
            }else{
                System.out.println("No match of input and existing ID of a serialize.");
                break;
            }
        }
    }

    public void list(){
        if(contactList.isEmpty()) {
            messenger.errListMsg();
        }else{
            for (Contact contact : contactList){        // <---- Om lista är tom, returna true, inom if{} displaya "listan är tom"
                contact.showDetails();
                messenger.contactDetailMessage();
            }
        }
    }

    public void search(String searchString){
        for(Contact contact : contactList){
            if(contact.getFirstName().toLowerCase().startsWith(searchString)){
                contact.showDetails();
                messenger.searchMessage();
            }else if (contact.getLastName().toLowerCase().startsWith(searchString)){
                contact.showDetails();
                messenger.searchMessage();
            }else
                messenger.searchNotFound();
        }
    }

    public void quit(){
        fileManager.save(contactList);
        System.exit(0);
    }
}
package nackademin.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.*;

public class Terminal extends TerminalMessage implements Serializable{
    private static final Logger logger = Logger.getLogger(Terminal.class.getName());



    public void MainMenu(){
        Client databaseContacts = new Client();
        Register register = new Register();
        AutoSaver autoSaver = new AutoSaver();
        autoSaver.autoSave(register.contactList);
        Client client = new Client();
        client.serverConnect();
        register.fileContactToContactList();
        while(true){
            try {
                Scanner userInput = new Scanner(System.in);
                String[] keyword = userInput.nextLine().split(" ");
                switch (keyword[0]) {
                    case "add":
                        if (keyword[3].isEmpty()) {
                            errAdd();
                        } else
                            register.add(keyword[1], keyword[2], keyword[3]);
                            logger.info("User input: " + keyword[0]);
                        break;

                    case "delete":
                            register.delete(keyword[1]);
                            deleteMessage();
                            logger.info("User input: " + keyword[0]);
                        break;

                    case "list":
                            register.list();
                            logger.info("User input: " + keyword[0]);
                        break;

                    case "search":
                        if (keyword[1].isEmpty())
                            errSearch();
                        else
                            register.search(keyword[1]);
                            logger.info("User input: " + keyword[0]);
                        break;

                    case "help":
                            helpMenu();
                            logger.info("User input: " + keyword[0]);
                        break;

                    case "quit":
                            quitMessage();
                            logger.info("Successfully terminated program.");
                            register.quit();
                        break;

                    default:
                        System.err.println("Not a valid input, try again\nWrite help to display valid commands.");
                        logger.log(Level.SEVERE,"User tried an invalid command: ", keyword[0]);
                        break;
                }
            }catch (ArrayIndexOutOfBoundsException ex){
                System.err.println("You have encountered an index error, Possibly due to lack of parameters.\n" +
                        "Type in help in order to know how to navigate through the menu.");
                logger.log(Level.SEVERE, "ArrayIndexOutOfBounds", ex);
            }
        }
    }
}

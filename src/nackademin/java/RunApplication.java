package nackademin.java;

import java.util.logging.Logger;

public class RunApplication {
    private static Logger logger = Logger.getLogger(RunApplication.class.getName());

    public void startProgram(){
        logger.info("Program started.");
        System.out.println("Welcome to the Adressbook!");
        Terminal adressBook = new Terminal();
        adressBook.MainMenu();

    }
}

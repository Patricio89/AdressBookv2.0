package nackademin.java;

public class TerminalMessage {
    public void addMessage(){
        System.out.println("\nContact added.");
    }
    public void deleteMessage(){
        System.out.println("\nContact deleted.");
    }
    public void contactDetailMessage(){
        System.out.println("\nEnd of contact details.\n------------------------------------------------");
    }

    public void searchMessage(){
        System.out.println("\nSearch complete.");
    }
    public void quitMessage(){
        System.out.println("\nTerminating program.");
    }
    public void noParameterMsg(){
        System.err.println("No parameters are allowed for this command.");
    }
    public void errListMsg(){
        System.out.println("The adressbook is currently empty.");
    }
    public void errAdd(){
        System.err.println("An error occured during the add function.\nMake sure you enter 3 parameters for this funtion.");
    }

    public void errDelete(){
        System.err.println("An error occured during the delete function.");
    }
    public void errSearch(){
        System.err.println("An error occured when using the Search function, The adressbook might be empty or keyword might not match.");
    }
    public void errQuit(){
        System.err.println("An error occured when trying to terminate the program, Do NOT use any parameters for this function.");
    }
    public void helpMenu(){
        System.out.println("[add] - Adds a new contact to the register.-- e.g: add Name Surname email\n");
        System.out.println("[delete] - Removes a existing contact in the register. -- e.g. delete contactID\n");
        System.out.println("[list] - Fetches all the stored contacts that is stored in the register.\n");
        System.out.println("[search] - Search through the register for a stored contact by either the contacts first or last name. -- e.g: search name\n");
        System.out.println("[quit] - Terminates the entire program + saves the current changes to the adressbook.");
    }
    public void searchNotFound(){
        System.out.println("Search index not found.");
    }

}

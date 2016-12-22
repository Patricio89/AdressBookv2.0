package nackademin.java;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoSaver {
   // Register register = new Register();
    SerializeContacts serialize = new SerializeContacts();
    private static Logger logger = Logger.getLogger(AutoSaver.class.getName());
    public void autoSave(ArrayList<Contact> contactList) {
        new Thread(() -> {
            while (true)
                try {
                    Thread.sleep(5000);
                    serialize.save(contactList);
                    // logger.info("Autosave complete.");
                } catch (InterruptedException ex) {
                logger.log(Level.SEVERE,"Autosave got interrupted.", ex);
                }
        }).start();
    }
}

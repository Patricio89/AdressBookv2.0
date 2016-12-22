package nackademin.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) {
        setupLogging();
        RunApplication application = new RunApplication();
        application.startProgram();

    }
    private static void setupLogging() {
        String loggingFilePath = "C:\\Users\\patri\\Desktop\\AdressBookv2.0\\logging.properties";
        try (FileInputStream fileInputStream = new FileInputStream(loggingFilePath)) {
            LogManager.getLogManager().readConfiguration(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not load log properties.", e);
        }
    }

}

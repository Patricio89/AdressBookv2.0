package nackademin.java;

import java.io.Serializable;
import java.util.UUID;

public class Contact implements Serializable{
    private String firstName;
    private String lastName;
    private String email;
    private UUID id;

    public Contact(String firstName, String lastName, String email) {
        generateID();
        id = getId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public UUID getId() {
        return id;
    }

    public void generateID(){
        this.id = UUID.randomUUID();
    }

    public void showDetails(){
        System.out.println("Contact ID: " + getId() + "\nFirst name: " + getFirstName() + "\nLast Name: " + getLastName() + "\nEmail: " + getEmail());
    }
}

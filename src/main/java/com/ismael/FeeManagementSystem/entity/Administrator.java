package com.ismael.FeeManagementSystem.entity;
import jakarta.persistence.*;
@Entity
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long administratorId;
    private String firstName;
    private String lastName;
    private String gender;

    @Column(name = "")
    private String contactInformation; // You can use an embedded class for contact info
    private String username;
    private String password; // Hashed and salted for security

    public Administrator() {
    }


    // Constructors, getters, and setters
    // ...


    public Administrator(Long administratorId, String firstName, String lastName, String gender, String contactInformation, String username, String password) {
        this.administratorId = administratorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.contactInformation = contactInformation;
        this.username = username;
        this.password = password;
    }

    public Long getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Long administratorId) {
        this.administratorId = administratorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
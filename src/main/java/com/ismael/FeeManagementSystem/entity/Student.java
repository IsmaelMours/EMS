package com.ismael.FeeManagementSystem.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String username; // Add username field
    private String password; // Add password field
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String contactInformation; // You can use an embedded class for contact info
    private String address;
    // Other relevant student details

    @OneToMany(mappedBy = "student")
    private List<Invoice> invoices;

    // Constructors, getters, and setters
    // ...
    public Student() {
    }

    public Student( String firstName, String lastName, String gender, String dateOfBirth, String contactInformation, String address) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.contactInformation = contactInformation;
        this.address = address;
    }

    // Getters and setters



    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
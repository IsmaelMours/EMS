package com.ismael.FeeManagementSystem.entity;

import jakarta.persistence.*;

import javax.security.auth.Subject;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;
    private Date issueDate;
    private Date dueDate;
    private double totalAmount;
    private String status;
    // Optional: Payment History can be a separate entity or a list of payments here

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToMany
    @JoinTable(
            name = "invoice_subject",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;

    @OneToMany(mappedBy = "invoice")
    private List<Payment> payments;

    // Constructors, getters, and setters
    // ...


    public Invoice() {
    }

    public Invoice(Long invoiceId, Date issueDate, Date dueDate, double totalAmount, String status, Student student, List<Subject> subjects, List<Payment> payments) {
        this.invoiceId = invoiceId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.student = student;
        this.subjects = subjects;
        this.payments = payments;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
package com.ismael.FeeManagementSystem.entity;

import java.util.Date;
import jakarta.persistence.*;
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private Date paymentDate;
    private double paymentAmount;

    @ManyToOne
    @JoinColumn(name = "invoiceId")
    private Invoice invoice;

    // Constructors, getters, and setters
    // ...


    public Payment() {
    }

    public Payment(Long paymentId, Date paymentDate, double paymentAmount, Invoice invoice) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.invoice = invoice;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
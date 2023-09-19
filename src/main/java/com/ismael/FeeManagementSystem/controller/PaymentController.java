package com.ismael.FeeManagementSystem.controller;

import com.ismael.FeeManagementSystem.entity.Payment;
import com.ismael.FeeManagementSystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process/{invoiceId}")
    public ResponseEntity<Payment> processPayment(@PathVariable Long invoiceId, @RequestBody Payment payment) {
        Payment processedPayment = paymentService.processPayment(invoiceId, payment);
        if (processedPayment != null) {
            return new ResponseEntity<>(processedPayment, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        if (payment != null) {
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/history/{invoiceId}")
    public ResponseEntity<List<Payment>> getPaymentHistoryForInvoice(@PathVariable Long invoiceId) {
        List<Payment> paymentHistory = paymentService.getPaymentHistoryForInvoice(invoiceId);
        if (paymentHistory != null) {
            return new ResponseEntity<>(paymentHistory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/outstanding/{invoiceId}")
    public ResponseEntity<Double> calculateOutstandingBalance(@PathVariable Long invoiceId) {
        double outstandingBalance = paymentService.calculateOutstandingBalance(invoiceId);
        return new ResponseEntity<>(outstandingBalance, HttpStatus.OK);
    }
}

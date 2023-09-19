package com.ismael.FeeManagementSystem.service;

import com.ismael.FeeManagementSystem.entity.Invoice;
import com.ismael.FeeManagementSystem.entity.Payment;
import com.ismael.FeeManagementSystem.repository.InvoiceRepository;
import com.ismael.FeeManagementSystem.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, InvoiceRepository invoiceRepository) {
        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
    }

    // Process a payment and update the invoice
    public Payment processPayment(Long invoiceId, Payment payment) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);

        if (invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();
            List<Payment> payments = invoice.getPayments();

            // Add the payment to the invoice's payment history
            payments.add(payment);

            // Update the invoice's payment history
            invoice.setPayments(payments);

            // Update the invoice
            invoiceRepository.save(invoice);

            // Save the payment
            return paymentRepository.save(payment);
        } else {
            // Invoice not found
            return null;
        }
    }

    // Retrieve payment details by ID
    public Payment getPaymentById(Long paymentId) {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        return payment.orElse(null);
    }

    // Retrieve payment history for an invoice
    public List<Payment> getPaymentHistoryForInvoice(Long invoiceId) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);

        if (invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();
            return invoice.getPayments();
        } else {
            // Invoice not found
            return null;
        }
    }

    // Calculate outstanding balance for an invoice
    public double calculateOutstandingBalance(Long invoiceId) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);

        if (invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();
            List<Payment> payments = invoice.getPayments();

            // Calculate the total payments made on the invoice
            double totalPayments = payments.stream()
                    .mapToDouble(Payment::getPaymentAmount)
                    .sum();

            // Calculate the outstanding balance
            return invoice.getTotalAmount() - totalPayments;
        } else {
            // Invoice not found
            return 0.0;
        }
    }
}

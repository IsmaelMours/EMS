package com.ismael.FeeManagementSystem.service;

import com.ismael.FeeManagementSystem.entity.Invoice;
import com.ismael.FeeManagementSystem.entity.Payment;
import com.ismael.FeeManagementSystem.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    // Create a new invoice
    public Invoice createInvoice(Invoice invoice) {
        // You can implement additional validation logic here
        return invoiceRepository.save(invoice);
    }

    // Calculate the total amount of an invoice
    public double calculateTotalAmount(Long invoiceId) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);

        if (invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();
            List<Payment> payments = invoice.getPayments();

            // Calculate the total amount based on payments
            double totalAmountPaid = payments.stream()
                    .mapToDouble(Payment::getPaymentAmount)
                    .sum();

            return invoice.getTotalAmount() - totalAmountPaid;
        } else {
            // Invoice not found
            return 0.0;
        }
    }

    // Add a payment to an invoice
    public Invoice addPaymentToInvoice(Long invoiceId, Payment payment) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);

        if (invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();
            List<Payment> payments = invoice.getPayments();

            // Add the payment to the invoice's payment history
            payments.add(payment);

            // Update the invoice's payment history
            invoice.setPayments(payments);

            // Update the invoice
            return invoiceRepository.save(invoice);
        } else {
            // Invoice not found
            return null;
        }
    }

    // Retrieve invoice details by ID
    public Invoice getInvoiceById(Long invoiceId) {
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        return invoice.orElse(null);
    }
    public Invoice updateInvoice(Invoice updatedInvoice) {
        // Check if the invoice exists
        Long invoiceId = updatedInvoice.getInvoiceId();
        Optional<Invoice> existingInvoiceOptional = invoiceRepository.findById(invoiceId);

        if (existingInvoiceOptional.isPresent()) {
            // Merge the updated fields into the existing invoice
            Invoice existingInvoice = existingInvoiceOptional.get();
            existingInvoice.setTotalAmount(updatedInvoice.getTotalAmount());
            // Set other fields as needed

            // Save the updated invoice
            return invoiceRepository.save(existingInvoice);
        } else {
            return null; // Invoice not found
        }
    }
    public boolean removeInvoice(Long invoiceId) {
        // Check if the invoice exists
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);

        if (invoiceOptional.isPresent()) {
            // If the invoice exists, delete it
            invoiceRepository.deleteById(invoiceId);
            return true;
        } else {
            return false; // Invoice not found
        }
    }

    // Retrieve all invoices
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
}

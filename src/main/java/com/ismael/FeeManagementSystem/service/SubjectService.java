package com.ismael.FeeManagementSystem.service;
import com.ismael.FeeManagementSystem.entity.Invoice;
import com.ismael.FeeManagementSystem.entity.Subject;
import com.ismael.FeeManagementSystem.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    // Add a new subject
    public Subject addSubject(Subject subject) {
        // You can implement additional validation logic here
        return subjectRepository.save(subject);
    }

    // Update subject details
    public Subject updateSubject(Subject updatedSubject) {
        // Check if the subject exists
        Optional<Subject> existingSubject = subjectRepository.findById(updatedSubject.getSubjectId());
        if (existingSubject.isPresent()) {
            Subject subjectToUpdate = existingSubject.get();

            // Update subject details based on the provided data
            subjectToUpdate.setSubjectName(updatedSubject.getSubjectName());
            subjectToUpdate.setSubjectCode(updatedSubject.getSubjectCode());
            subjectToUpdate.setDescription(updatedSubject.getDescription());

            // Save the updated subject
            return subjectRepository.save(subjectToUpdate);
        } else {
            // Subject not found
            return null;
        }
    }

    // Associate subjects with an invoice
    public Subject associateSubjectsWithInvoice(Long subjectId, Invoice invoice) {
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);

        if (subjectOptional.isPresent()) {
            Subject subject = subjectOptional.get();

            // Add the subject to the invoice's subjects list
            invoice.getSubjects().add(subject);

            // Update the invoice
            // Assuming you have an InvoiceService for managing invoices
            // You can call the appropriate method here to update the invoice

            return subject;
        } else {
            // Subject not found
            return null;
        }
    }

    // Retrieve subject details by ID
    public Subject getSubjectById(Long subjectId) {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        return subject.orElse(null);
    }
    public boolean removeSubject(Long subjectId) {
        // Check if the subject exists
        if (subjectRepository.existsById(subjectId)) {
            subjectRepository.deleteById(subjectId);
            return true;
        } else {
            return false; // Subject not found
        }
    }

    // Retrieve all subjects
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

}

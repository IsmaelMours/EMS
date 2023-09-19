package com.ismael.FeeManagementSystem.repository;

import com.ismael.FeeManagementSystem.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    // You can define custom query methods here if needed
}

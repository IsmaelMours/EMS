package com.ismael.FeeManagementSystem.repository;

import com.ismael.FeeManagementSystem.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    // You can define custom query methods here if needed
}
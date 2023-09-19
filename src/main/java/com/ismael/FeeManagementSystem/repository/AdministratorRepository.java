package com.ismael.FeeManagementSystem.repository;

import com.ismael.FeeManagementSystem.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    // You can define custom query methods here if needed
}


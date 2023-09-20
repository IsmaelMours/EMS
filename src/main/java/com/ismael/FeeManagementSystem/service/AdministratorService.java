package com.ismael.FeeManagementSystem.service;


import com.ismael.FeeManagementSystem.entity.Administrator;
import com.ismael.FeeManagementSystem.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {
    private final AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    // Create a new administrator account
    public Administrator createAdministrator(Administrator administrator) {
        // You can save the password as it is without encoding
        return administratorRepository.save(administrator);
    }

    // Update administrator account details
    public Administrator updateAdministrator(Administrator updatedAdministrator) {
        // Check if the administrator exists
        Optional<Administrator> existingAdministrator = administratorRepository.findById(updatedAdministrator.getAdministratorId());
        if (existingAdministrator.isPresent()) {
            Administrator administratorToUpdate = existingAdministrator.get();

            // Update administrator details based on the provided data
            administratorToUpdate.setFirstName(updatedAdministrator.getFirstName());
            administratorToUpdate.setLastName(updatedAdministrator.getLastName());
            administratorToUpdate.setGender(updatedAdministrator.getGender());
            administratorToUpdate.setContactInformation(updatedAdministrator.getContactInformation());
            administratorToUpdate.setUsername(updatedAdministrator.getUsername());

            // Update the password if provided
            if (updatedAdministrator.getPassword() != null) {
                administratorToUpdate.setPassword(updatedAdministrator.getPassword());
            }

            // Save the updated administrator
            return administratorRepository.save(administratorToUpdate);
        } else {
            // Administrator not found
            return null;
        }
    }

    // Retrieve administrator details by ID
    public Administrator getAdministratorById(Long administratorID) {
        Optional<Administrator> administrator = administratorRepository.findById(administratorID);
        return administrator.orElse(null);
    }

    // Retrieve all administrators
    public List<Administrator> getAllAdministrators() {
        return administratorRepository.findAll();
    }
}

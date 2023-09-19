package com.ismael.FeeManagementSystem.service;

import com.ismael.FeeManagementSystem.entity.Administrator;
import com.ismael.FeeManagementSystem.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {
    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdministratorService(
            AdministratorRepository administratorRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.administratorRepository = administratorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Create a new administrator account
    public Administrator createAdministrator(Administrator administrator) {
        // Encode the administrator's password before saving to the database
        administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
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

            // Encode the updated password if provided
            if (updatedAdministrator.getPassword() != null) {
                administratorToUpdate.setPassword(passwordEncoder.encode(updatedAdministrator.getPassword()));
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


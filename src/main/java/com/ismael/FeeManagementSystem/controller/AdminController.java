package com.ismael.FeeManagementSystem.controller;
import com.ismael.FeeManagementSystem.entity.Administrator;
import com.ismael.FeeManagementSystem.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrators")
public class AdminController {
    private final AdministratorService administratorService;

    @Autowired
    public AdminController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @PostMapping("/create")
    public ResponseEntity<Administrator> createAdministrator(@RequestBody Administrator administrator) {
        Administrator createdAdministrator = administratorService.createAdministrator(administrator);
        return new ResponseEntity<>(createdAdministrator, HttpStatus.CREATED);
    }

    @GetMapping("/{administratorId}")
    public ResponseEntity<Administrator> getAdministratorById(@PathVariable Long administratorId) {
        Administrator administrator = administratorService.getAdministratorById(administratorId);
        if (administrator != null) {
            return new ResponseEntity<>(administrator, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Administrator>> getAllAdministrators() {
        List<Administrator> administrators = administratorService.getAllAdministrators();
        return new ResponseEntity<>(administrators, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Administrator> updateAdministrator(@RequestBody Administrator updatedAdministrator) {
        Administrator updated = administratorService.updateAdministrator(updatedAdministrator);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

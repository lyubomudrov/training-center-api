package com.example.training_center_api.controller;

import com.example.training_center_api.model.Registration;
import com.example.training_center_api.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    @GetMapping("/{id}")
    public Optional<Registration> getRegistrationById(@PathVariable Long id) {
        return registrationService.getRegistrationById(id);
    }

    @PostMapping
    public Registration createRegistration(@RequestBody Registration registration) {
        return registrationService.saveRegistration(registration);
    }

    @DeleteMapping("/{id}")
    public void deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
    }
}

package com.example.training_center_api.controller;

import com.example.training_center_api.model.Registration;
import com.example.training_center_api.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registrations")
class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registration> getRegistrationById(@PathVariable Long id) {
        Optional<Registration> reg = registrationService.getRegistrationById(id);
        return reg.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Registration> createRegistration(@RequestBody Registration registration) {
        Registration saved = registrationService.saveRegistration(registration);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(@PathVariable Long id, @RequestBody Registration registration) {
        if (!registrationService.getRegistrationById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        registration.setId(id);
        Registration updated = registrationService.saveRegistration(registration);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        if (!registrationService.getRegistrationById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        registrationService.deleteRegistration(id);
        return ResponseEntity.noContent().build();
    }
}

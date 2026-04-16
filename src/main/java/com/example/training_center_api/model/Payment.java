package com.example.training_center_api.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String status;

    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

    public Payment() {
        this.createdAt = LocalDateTime.now();
    }

    public Payment(Double amount, String status, Registration registration) {
        this.amount = amount;
        this.status = status;
        this.registration = registration;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }
}
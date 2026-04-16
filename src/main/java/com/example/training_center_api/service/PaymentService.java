package com.example.training_center_api.service;

import com.example.training_center_api.model.Payment;
import com.example.training_center_api.model.Registration;
import com.example.training_center_api.repository.PaymentRepository;
import com.example.training_center_api.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RegistrationRepository registrationRepository;

    public PaymentService(PaymentRepository paymentRepository,
                          RegistrationRepository registrationRepository) {
        this.paymentRepository = paymentRepository;
        this.registrationRepository = registrationRepository;
    }

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    public Payment getById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public Payment create(Long registrationId, Payment payment) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));

        payment.setRegistration(registration);
        return paymentRepository.save(payment);
    }

    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}
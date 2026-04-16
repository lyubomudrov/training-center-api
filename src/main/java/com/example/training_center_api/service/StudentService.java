package com.example.training_center_api.service;

import com.example.training_center_api.model.Student;
import com.example.training_center_api.repository.RegistrationRepository;
import com.example.training_center_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Autowired
    private RegistrationRepository registrationRepository;

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Студент не найден"));

        boolean hasRegistrations = registrationRepository.existsByStudent(student);
        if (hasRegistrations) {
            throw new IllegalStateException("Нельзя удалить студента, у которого есть регистрации.");
        }

        studentRepository.delete(student);
    }
}
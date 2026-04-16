package com.example.training_center_api.repository;

import com.example.training_center_api.model.Course;
import com.example.training_center_api.model.Registration;
import com.example.training_center_api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByCourse(Course course);
    boolean existsByStudent(Student student);
}

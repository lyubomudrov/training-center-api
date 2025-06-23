package com.example.training_center_api.repository;

import com.example.training_center_api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByNameContainingIgnoreCase(String name);
    List<Course> findByIsActiveTrue();
}

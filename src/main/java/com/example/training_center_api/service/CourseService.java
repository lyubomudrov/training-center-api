package com.example.training_center_api.service;

import com.example.training_center_api.model.Course;
import com.example.training_center_api.repository.CourseRepository;
import com.example.training_center_api.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Autowired
    private RegistrationRepository registrationRepository;

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Курс не найден"));

        boolean hasRegistrations = registrationRepository.existsByCourse(course);
        if (hasRegistrations) {
            throw new IllegalStateException("Нельзя удалить курс, у которого есть регистрации.");
        }

        courseRepository.delete(course);
    }


}

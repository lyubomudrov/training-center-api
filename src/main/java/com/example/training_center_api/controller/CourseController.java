package com.example.training_center_api.controller;

import com.example.training_center_api.model.Course;
import com.example.training_center_api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Read: Получить все курсы
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Read: Получить курс по ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create: Добавить новый курс
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course savedCourse = courseService.saveCourse(course);
        return ResponseEntity.ok(savedCourse);
    }

    // Update: Полное обновление курса
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        if (!courseService.getCourseById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        course.setId(id);
        Course updatedCourse = courseService.saveCourse(course);
        return ResponseEntity.ok(updatedCourse);
    }

    // Delete: Удалить курс
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        if (!courseService.getCourseById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
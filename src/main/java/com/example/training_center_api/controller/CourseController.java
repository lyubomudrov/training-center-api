package com.example.training_center_api.controller;

import com.example.training_center_api.model.Course;
import com.example.training_center_api.model.Teacher;
import com.example.training_center_api.repository.TeacherRepository;
import com.example.training_center_api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.training_center_api.dto.CourseRequestDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final TeacherRepository teacherRepository;
    @Autowired
    public CourseController(CourseService courseService, TeacherRepository teacherRepository) {
        this.courseService = courseService;
        this.teacherRepository = teacherRepository;
    }

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
    public ResponseEntity<Course> createCourse(@RequestBody CourseRequestDTO dto) {
        Course course = new Course();
        course.setName(dto.name);
        course.setDescription(dto.description);
        course.setActive(dto.active);

        if (dto.teacherId != null) {
            Teacher teacher = teacherRepository.findById(dto.teacherId)
                    .orElseThrow(() -> new RuntimeException("Преподаватель не найден по id = " + dto.teacherId));
            course.setTeacher(teacher);
        }

        return ResponseEntity.ok(courseService.saveCourse(course));
    }

    // Update: Полное обновление курса
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDTO dto) {
        Optional<Course> existingCourseOpt = courseService.getCourseById(id);
        if (existingCourseOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course course = existingCourseOpt.get();
        course.setName(dto.name);
        course.setDescription(dto.description);
        course.setActive(dto.active);

        if (dto.teacherId != null) {
            Teacher teacher = teacherRepository.findById(dto.teacherId)
                    .orElseThrow(() -> new RuntimeException("Преподаватель не найден по id = " + dto.teacherId));
            course.setTeacher(teacher);
        } else {
            course.setTeacher(null);
        }

        return ResponseEntity.ok(courseService.saveCourse(course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
        }
    }
}
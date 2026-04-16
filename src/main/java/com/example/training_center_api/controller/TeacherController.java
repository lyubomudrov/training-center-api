package com.example.training_center_api.controller;

import com.example.training_center_api.model.Course;
import com.example.training_center_api.model.Teacher;
import com.example.training_center_api.repository.CourseRepository;
import com.example.training_center_api.repository.TeacherRepository;
import com.example.training_center_api.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherController(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        return teacher.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        try {
            if (teacher == null || teacher.getName() == null || teacher.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Teacher savedTeacher = teacherService.saveTeacher(teacher);
            return ResponseEntity.ok(savedTeacher);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        try {
            if (teacher == null || teacher.getName() == null || teacher.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Optional<Teacher> existingTeacher = teacherService.getTeacherById(id);
            if (!existingTeacher.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            teacher.setId(id);
            Teacher updated = teacherService.saveTeacher(teacher);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        // Проверка, существует ли преподаватель
        if (!teacherRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Проверка: есть ли курсы, где используется этот преподаватель
        List<Course> courses = courseRepository.findByTeacherId(id);
        if (!courses.isEmpty()) {
            return ResponseEntity.status(409).body("Нельзя удалить преподавателя — он привязан к курсам.");
        }

        // Удаление
        teacherRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
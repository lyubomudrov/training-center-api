package com.example.training_center_api.controller;

import com.example.training_center_api.model.Teacher;
import com.example.training_center_api.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

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
        Teacher saved = teacherService.saveTeacher(teacher);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        if (!teacherService.getTeacherById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        teacher.setId(id);
        Teacher updated = teacherService.saveTeacher(teacher);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        if (!teacherService.getTeacherById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
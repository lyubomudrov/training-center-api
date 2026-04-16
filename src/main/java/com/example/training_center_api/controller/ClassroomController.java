package com.example.training_center_api.controller;

import com.example.training_center_api.model.Classroom;
import com.example.training_center_api.service.ClassroomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public List<Classroom> getAll() {
        return classroomService.getAll();
    }

    @GetMapping("/{id}")
    public Classroom getById(@PathVariable Long id) {
        return classroomService.getById(id);
    }

    @PostMapping
    public Classroom create(@RequestBody Classroom classroom) {
        return classroomService.create(classroom);
    }

    @PutMapping("/{id}")
    public Classroom update(@PathVariable Long id, @RequestBody Classroom classroom) {
        return classroomService.update(id, classroom);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        classroomService.delete(id);
    }
}
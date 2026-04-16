package com.example.training_center_api.service;

import com.example.training_center_api.model.Classroom;
import com.example.training_center_api.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public List<Classroom> getAll() {
        return classroomRepository.findAll();
    }

    public Classroom getById(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classroom not found"));
    }

    public Classroom create(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public Classroom update(Long id, Classroom updated) {
        Classroom classroom = getById(id);
        classroom.setName(updated.getName());
        classroom.setCapacity(updated.getCapacity());
        return classroomRepository.save(classroom);
    }

    public void delete(Long id) {
        classroomRepository.deleteById(id);
    }
}
package com.example.training_center_api.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "registration")
public class Registration {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    @Column(name = "completion_status")
    private String completionStatus;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }
    public String getCompletionStatus() { return completionStatus; }
    public void setCompletionStatus(String completionStatus) { this.completionStatus = completionStatus; }
}
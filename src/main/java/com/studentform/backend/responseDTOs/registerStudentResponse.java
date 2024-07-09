package com.studentform.backend.responseDTOs;

import com.studentform.backend.entities.Students;

import lombok.Data;

@Data
public class registerStudentResponse{
    Students student;
    private boolean success;
    private String message;

    public void setStudents(Students students) {
        this.student = students;
    }
}
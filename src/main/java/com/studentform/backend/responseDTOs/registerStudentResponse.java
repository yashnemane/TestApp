package com.studentform.backend.responseDTOs;

import com.studentform.backend.entities.Students;
import lombok.Setter;

import lombok.Data;

@Data
public class registerStudentResponse{
    Students student;
    private boolean success;

    public void setStudents(Students students) {
        this.student = students;
    }
}
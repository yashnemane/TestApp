package com.studentform.backend.responseDTOs;

import com.studentform.backend.entities.Students;

import lombok.Data;

@Data
public class getStudentResponse{
    Students students;
    boolean success;
    String message;

    public void setStudents(Students students) {
        this.students = students;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
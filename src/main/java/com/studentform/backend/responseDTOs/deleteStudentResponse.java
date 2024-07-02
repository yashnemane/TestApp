package com.studentform.backend.responseDTOs;


import com.studentform.backend.entities.Students;
import lombok.Data;

@Data
public class deleteStudentResponse{
    Students student;
    private boolean isDeleted;
    private String message;
}
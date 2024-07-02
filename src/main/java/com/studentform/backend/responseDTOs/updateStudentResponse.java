package com.studentform.backend.responseDTOs;

import lombok.Data;
import com.studentform.backend.entities.Students;

@Data
public class updateStudentResponse{
    Students student;
    private boolean success;
}
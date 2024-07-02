package com.studentform.backend.responseDTOs;

import com.studentform.backend.entities.Students;
import java.util.*;

public class getStudentResponse{
    Students students;
    private Long id;
    private String firstName;
    private String lastName;
    private String targetExam;
    private Long grade;
    private Long mobileNo;
    private boolean success;
    private String message;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
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
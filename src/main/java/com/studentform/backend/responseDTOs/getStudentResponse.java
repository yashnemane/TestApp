package com.studentform.backend.responseDTOs;

import com.studentform.backend.entities.Students;
import java.util.*;

public class getStudentResponse{
    List<Students> students;
    private Long id;
    private String firstName;
    private String lastName;
    private String targetExam;
    private Long grade;
    private Long mobileNo;

    public void setFirstName(String firstName) {
        firstName = this.firstName;
    }
}
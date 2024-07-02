package com.studentform.backend.requestDTOs;

import lombok.Data;

@Data
public class updateStudentRequest{
    private Long id;
    private Long mobileNo;
    private String firstName;
    private String lastName;
    private Long grade;
    private String targetExam;
}
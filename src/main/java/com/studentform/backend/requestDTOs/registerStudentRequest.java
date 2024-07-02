package com.studentform.backend.requestDTOs;

import lombok.Data;

@Data
public class registerStudentRequest{
    private Long id;
    private String firstName;
    private String lastName;
    private Long grade;
    private String targetExam;
    private Long mobileNo;
}
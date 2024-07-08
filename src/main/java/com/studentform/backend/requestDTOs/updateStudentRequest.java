package com.studentform.backend.requestDTOs;

import lombok.Data;

@Data
public class updateStudentRequest{
    public updateStudentRequest(String firstName, String lastName, long grade, String targetExam, long mobileNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.targetExam = targetExam;
        this. mobileNo = mobileNo;
    }
    private Long id;
    private Long mobileNo;
    private String firstName;
    private String lastName;
    private Long grade;
    private String targetExam;
}
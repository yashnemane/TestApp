package com.studentform.backend.requestDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class registerStudentRequest{
    private Long id;
    private String firstName;
    private String lastName;
    private Long grade;
    private String targetExam;
    private Long mobileNo;

    public registerStudentRequest(String firstName, String lastName, Long grade, String targetExam, Long mobileNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.targetExam = targetExam;
        this.mobileNo = mobileNo;
    }
}
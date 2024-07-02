package com.studentform.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Table(name = "students")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "grade")
    private Long grade;

    @Column(name = "target_exam")
    private String targetExam;

    @Column(name = "mobile_no")
    private Long mobileNo;


    public void setGrade() {
        grade = this.grade;
    }
    public void setMobileNo() {
        mobileNo = this.mobileNo;
    }
    public void setTargetExam() {
        targetExam = this.targetExam;
    }
    public void setLastName() {
        lastName = this.lastName;
    }
    public void setFirstName() {
        firstName = this.firstName;
    }
}

package com.studentform.backend.service;

import com.studentform.backend.entities.Students;
import com.studentform.backend.requestDTOs.registerStudentRequest;
import com.studentform.backend.requestDTOs.updateStudentRequest;
import com.studentform.backend.responseDTOs.allStudentsResponse;
import com.studentform.backend.responseDTOs.deleteStudentResponse;
import com.studentform.backend.responseDTOs.getStudentResponse;
import com.studentform.backend.responseDTOs.updateStudentResponse;
import com.studentform.backend.responseDTOs.registerStudentResponse;

import java.util.List;

public interface StudentService {
    allStudentsResponse getAllStudents();
    getStudentResponse getStudentById(Long id);
    registerStudentResponse createStudent(registerStudentRequest students);
    updateStudentResponse updateStudent(Long id, updateStudentRequest updateStudentRequest);
    deleteStudentResponse deleteStudent(Long id);
}
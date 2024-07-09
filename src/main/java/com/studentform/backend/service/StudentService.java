package com.studentform.backend.service;

import java.util.List;

import com.studentform.backend.requestDTOs.registerStudentRequest;
import com.studentform.backend.requestDTOs.updateStudentRequest;
import com.studentform.backend.responseDTOs.allStudentsResponse;
import com.studentform.backend.responseDTOs.deleteStudentResponse;
import com.studentform.backend.responseDTOs.getStudentMarksResponse;
import com.studentform.backend.responseDTOs.getStudentResponse;
import com.studentform.backend.responseDTOs.updateStudentResponse;
import com.studentform.backend.responseDTOs.registerStudentResponse;

public interface StudentService {
    allStudentsResponse getAllStudents();
    getStudentResponse getStudentById(Long studentId);
    registerStudentResponse createStudent(registerStudentRequest students);
    updateStudentResponse updateStudent(Long id, updateStudentRequest updateStudentRequest);
    deleteStudentResponse deleteStudent(Long mobileNo);
    List<getStudentMarksResponse> getStudentMarks();
}
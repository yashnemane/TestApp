package com.studentform.backend.serviceimpl;

import com.studentform.backend.entities.Students;
import com.studentform.backend.repositories.StudentRepository;
import com.studentform.backend.requestDTOs.registerStudentRequest;
import com.studentform.backend.requestDTOs.updateStudentRequest;
import com.studentform.backend.responseDTOs.allStudentsResponse;
import com.studentform.backend.responseDTOs.deleteStudentResponse;
import com.studentform.backend.responseDTOs.getStudentMarksResponse;
import com.studentform.backend.responseDTOs.getStudentResponse;
import com.studentform.backend.responseDTOs.registerStudentResponse;
import com.studentform.backend.responseDTOs.updateStudentResponse;
import com.studentform.backend.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String API_URL = "https://demo3839297.mockable.io/getStudentMarks";

    public StudentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<getStudentMarksResponse> getStudentMarks() {
        getStudentMarksResponse[] response = restTemplate.getForObject(API_URL, getStudentMarksResponse[].class);
        return Arrays.asList(response);
    }

    @Override
    public allStudentsResponse getAllStudents() {
        allStudentsResponse response = new allStudentsResponse();
        List<Students> students = Collections.emptyList();
        students = studentRepository.findAll();
        if(!students.isEmpty()){
            response.setStudents(students);
            response.setSuccess(true);
            response.setMessage("All students fetched successfully.");
        } else{
            response.setStudents(students);
            response.setMessage("No students registered.");
            response.setSuccess(false);
        }

        return response;
    }

    @Override
    public getStudentResponse getStudentById(Long studentId) {
        getStudentResponse response = new getStudentResponse();
        Students student = null;
        student = studentRepository.findStudentById(studentId);
        if(student!=null){
            response.setStudents(student);
            response.setSuccess(true);
            response.setMessage("Successfully fetched.");
        } else {
            response.setSuccess(false);
            response.setMessage("No registered student.");
        }

        return response;
    }

    @Override
    public registerStudentResponse createStudent(registerStudentRequest students) {
        registerStudentResponse response = new registerStudentResponse();
        Students student = new Students();
        Students ifAlready = null;
        ifAlready=studentRepository.findStudentByMobileNo(students.getMobileNo());
        if (ifAlready != null) {
            response.setStudent(ifAlready);
            response.setMessage("User already registered with same mobile number.");
            response.setSuccess(false);
        } else if (ifAlready==null && students.getFirstName() != null && students.getLastName() != null && students.getGrade() != null 
        && students.getMobileNo() != null && students.getTargetExam() != null) {
            student.setFirstName(students.getFirstName());
            student.setLastName(students.getLastName());
            student.setGrade(students.getGrade());
            student.setTargetExam(students.getTargetExam());
            student.setMobileNo(students.getMobileNo());
            studentRepository.save(student);
            Students savedStudent = studentRepository.findStudentByMobileNo(student.getMobileNo());
            response.setStudents(savedStudent);
            response.setSuccess(true);
            response.setMessage("User registered successfully.");
        } else {
            response.setSuccess(false);
            response.setMessage("Please enter all details.");
        }
        
        return response;
    }

    @Override
    public updateStudentResponse updateStudent(Long id, updateStudentRequest studentDetails) {
        updateStudentResponse response = new updateStudentResponse();
        Students student = studentRepository.findStudentById(id);

        if(studentDetails.getFirstName() != null){
            student.setFirstName(studentDetails.getFirstName());
        }
        if(studentDetails.getLastName() != null){
            student.setLastName(studentDetails.getLastName());
        }
        if(studentDetails.getGrade() != 0){
            student.setGrade(studentDetails.getGrade());
        }
        if(studentDetails.getTargetExam() != null){
            student.setTargetExam(studentDetails.getTargetExam());
        }
        if(studentDetails.getMobileNo() != 0){
            student.setMobileNo(studentDetails.getMobileNo());
        }
        
        studentRepository.save(student);
        student = studentRepository.findStudentById(id);
        response.setStudent(student);
        response.setSuccess(true);
        response.setMessage("Record updated successfully.");
        
        return response;
    }

    @Override
    public deleteStudentResponse deleteStudent(Long mobileNo) {
        Students student = studentRepository.findStudentByMobileNo(mobileNo);
        deleteStudentResponse response = new deleteStudentResponse();
        if(student!=null){
            studentRepository.delete(student);
            response.setStudent(student);
            response.setDeleted(true);
            response.setMessage("Record Deleted successfully.");
        } else {
            response.setDeleted(false);
            response.setMessage("No student found.");
        }
        
        return response;
    }
}
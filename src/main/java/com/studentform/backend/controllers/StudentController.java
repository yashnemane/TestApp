package com.studentform.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studentform.backend.requestDTOs.deleteStudentRequest;
import com.studentform.backend.requestDTOs.getStudentRequest;
import com.studentform.backend.requestDTOs.registerStudentRequest;
import com.studentform.backend.requestDTOs.updateStudentRequest;
import com.studentform.backend.responseDTOs.allStudentsResponse;
import com.studentform.backend.responseDTOs.deleteStudentResponse;
import com.studentform.backend.responseDTOs.getStudentResponse;
import com.studentform.backend.responseDTOs.registerStudentResponse;
import com.studentform.backend.responseDTOs.updateStudentResponse;
import com.studentform.backend.entities.Students;
import com.studentform.backend.service.StudentService;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/get/students")
    public allStudentsResponse getAllStudents() {
        allStudentsResponse students = studentService.getAllStudents();
        return students;
    }

    @GetMapping("/get/{student_id}")
    public getStudentResponse getStudentById(@PathVariable("student_id") Long studentId) {
        getStudentResponse student = studentService.getStudentById(studentId);
        return student;
    }
            

    @PostMapping(value = "/registerStudent")
        public registerStudentResponse createStudent(
            @RequestBody registerStudentRequest req) {
            return studentService.createStudent(req);
        }

    @PutMapping(value = "/update/{student_id}")
        public updateStudentResponse updateStudent(
            @PathVariable("student_id") Long studentId,
            @RequestBody updateStudentRequest req) {
            return studentService.updateStudent(studentId, req);
        }

    @DeleteMapping(value = "/delete/{student_id}")
        public deleteStudentResponse deleteStudent(
            @PathVariable("student_id") Long studentId) {
            return studentService.deleteStudent(studentId);
        }
}
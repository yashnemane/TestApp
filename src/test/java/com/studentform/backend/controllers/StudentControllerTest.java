package com.studentform.backend.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentform.backend.entities.Students;
import com.studentform.backend.repositories.StudentRepository;
import com.studentform.backend.requestDTOs.registerStudentRequest;
import com.studentform.backend.requestDTOs.updateStudentRequest;
import com.studentform.backend.responseDTOs.*;
import com.studentform.backend.service.StudentService;

import antlr.collections.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents() throws Exception {
        allStudentsResponse response = new allStudentsResponse();
        response.setStudents(Arrays.asList(
            new Students(1L, "John", "Doe", 10L, "Exam1", 1234567890L),
            new Students(2L, "Jane", "Doe", 12L, "Exam2", 1234567891L)
        ));
        response.setSuccess(true);
        response.setMessage("All students fetched successfully.");

        when(studentService.getAllStudents()).thenReturn(response);

        mockMvc.perform(get("/api/students/get/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("All students fetched successfully."))
                .andExpect(jsonPath("$.students[0].firstName").value("John"))
                .andExpect(jsonPath("$.students[1].firstName").value("Jane"));
    }

    @Test
    void testGetStudent() throws Exception {
        getStudentResponse response = new getStudentResponse();
        response.setStudents(new Students(1L, "John", "Doe", 10L, "Exam1", 1234567890L));
        response.setSuccess(true);
        response.setMessage("Successfully fetched.");

        when(studentService.getStudentById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/students/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Successfully fetched."))
                .andExpect(jsonPath("$.students.firstName").value("John"));
    }

   @Test
    void testCreateStudent() throws Exception {
        registerStudentRequest request = new registerStudentRequest("John", "Doe", 10L, "Exam1", 1234567890L);
        registerStudentResponse response = new registerStudentResponse();
        response.setStudents(new Students(1L, "John", "Doe", 10L, "Exam1", 1234567890L));
        response.setSuccess(true);
        response.setMessage("User registered successfully.");

        when(studentService.createStudent(request)).thenReturn(response);

        mockMvc.perform(post("/api/students/registerStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("User registered successfully."))
                .andExpect(jsonPath("$.student.firstName").value("John"));
    }

    @Test
    void testUpdateStudent() throws Exception {
        updateStudentRequest request = new updateStudentRequest("John", "Doe", 11L, "Exam2", 1234567890L);
        updateStudentResponse response = new updateStudentResponse();
        response.setStudent(new Students(1L, "John", "Doe", 11L, "Exam2", 1234567890L));
        response.setSuccess(true);
        response.setMessage("Record updated successfully.");

        when(studentService.updateStudent(1L, request)).thenReturn(response);

        mockMvc.perform(put("/api/students/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Record updated successfully."))
                .andExpect(jsonPath("$.student.grade").value(11L));
    }

    @Test
    void testDeleteStudent() throws Exception {

        deleteStudentResponse response = new deleteStudentResponse();
        response.setDeleted(true);
        response.setMessage("Record Deleted successfully.");

        when(studentService.deleteStudent(1234567890L)).thenReturn(response);

        mockMvc.perform(delete("/api/students/delete/1234567890"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deleted").value(true))
                .andExpect(jsonPath("$.message").value("Record Deleted successfully."));
    }
}
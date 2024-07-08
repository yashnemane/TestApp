package com.studentform.backend.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

//import com.studentform.backend.TestApiApplication;
import com.studentform.backend.entities.Students;
import com.studentform.backend.repositories.StudentRepository;
import com.studentform.backend.requestDTOs.registerStudentRequest;
import com.studentform.backend.requestDTOs.updateStudentRequest;
import com.studentform.backend.responseDTOs.allStudentsResponse;
import com.studentform.backend.responseDTOs.deleteStudentResponse;
import com.studentform.backend.responseDTOs.getStudentResponse;
import com.studentform.backend.responseDTOs.registerStudentResponse;
import com.studentform.backend.responseDTOs.updateStudentResponse;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudentsWithStudents() {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(
            new Students(1L, "John", "Doe", 10L, "Exam1", 1234567890L),
            new Students(2L, "Jane", "Doe", 12L, "Exam2", 1234567891L)
            ));

        allStudentsResponse response = studentService.getAllStudents();

        assertTrue(response.isSuccess());
        assertEquals("All students fetched successfully.", response.getMessage());
        assertEquals(2, response.getStudents().size());
        assertEquals("John", response.getStudents().get(0).getFirstName());
        assertEquals("Jane", response.getStudents().get(1).getFirstName());
    }

    @Test
    void testGetAllStudentsWithNoStudents() {
        when(studentRepository.findAll()).thenReturn(Collections.emptyList());

        allStudentsResponse response = studentService.getAllStudents();

        assertTrue(!response.isSuccess());
        assertEquals("No students registered.", response.getMessage());
        assertEquals(0, response.getStudents().size());
    }

    @Test
    void testGetStudentByIdWithExistingStudent() {
        Students student = new Students(1L,"John", "Doe", 10L, "Exam1", 1234567890L);
        when(studentRepository.findStudentById(1L)).thenReturn(student);

        getStudentResponse response = studentService.getStudentById(1L);

        assertTrue(response.isSuccess());
        assertEquals("Successfully fetched.", response.getMessage());
        assertEquals("John", response.getStudents().getFirstName());
    }

    @Test
    void testGetStudentByIdWithNonExistingStudent() {
        when(studentRepository.findStudentById(1L)).thenReturn(null);

        getStudentResponse response = studentService.getStudentById(1L);

        assertTrue(!response.isSuccess());
        assertEquals("No registered student.", response.getMessage());
    }

    @Test
    void testCreateStudentWithNewStudent() {
        registerStudentRequest request = new registerStudentRequest("John", "Doe", 10L, "Exam1", 1234567890L);
        Students student = new Students(1L,"John", "Doe", 10L, "Exam1", 1234567890L);

        when(studentRepository.findStudentByMobileNo(1234567890L)).thenReturn(null).thenReturn(student);
        when(studentRepository.save(any(Students.class))).thenReturn(student);

        registerStudentResponse response = studentService.createStudent(request);

        assertTrue(response.isSuccess());
        assertEquals("User registered successfully.", response.getMessage());
        assertEquals("John", response.getStudent().getFirstName());
    }

    @Test
    void testCreateStudentWithExistingStudent() {
        registerStudentRequest request = new registerStudentRequest("John", "Doe", 10L, "Exam1", 1234567890L);
        Students existingStudent = new Students(1L,"John", "Doe", 10L, "Exam1", 1234567890L);
        when(studentRepository.findStudentByMobileNo(1234567890L)).thenReturn(existingStudent);

        registerStudentResponse response = studentService.createStudent(request);

        assertTrue(!response.isSuccess());
        assertEquals("User already registered with same mobile number.", response.getMessage());
    }

    @Test
    void testUpdateStudent() {
        updateStudentRequest request = new updateStudentRequest("John", "Smith", 11L, "Exam2", 1234567890L);
        Students student = new Students(1L,"John", "Doe", 10L, "Exam1", 1234567890L);
        when(studentRepository.findStudentById(1L)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);

        updateStudentResponse response = studentService.updateStudent(1L, request);

        assertTrue(response.isSuccess());
        assertEquals("Record updated successfully.", response.getMessage());
        assertEquals("Smith", response.getStudent().getLastName());
    }

    @Test
    void testDeleteStudent() {
        Students student = new Students(1L, "John", "Doe", 10L, "Exam1", 1234567890L);
        when(studentRepository.findStudentByMobileNo(1234567890L)).thenReturn(student);
        doNothing().when(studentRepository).delete(student);

        deleteStudentResponse response = studentService.deleteStudent(1234567890L);

        assertTrue(response.isDeleted());
        assertEquals("Record Deleted successfully.", response.getMessage());
    }
}

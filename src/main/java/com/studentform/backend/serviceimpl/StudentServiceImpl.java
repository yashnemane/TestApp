package com.studentform.backend.serviceimpl;

import com.studentform.backend.entities.Students;
import com.studentform.backend.repositories.StudentRepository;
import com.studentform.backend.requestDTOs.registerStudentRequest;
import com.studentform.backend.requestDTOs.updateStudentRequest;
import com.studentform.backend.responseDTOs.deleteStudentResponse;
import com.studentform.backend.responseDTOs.getStudentResponse;
import com.studentform.backend.responseDTOs.registerStudentResponse;
import com.studentform.backend.responseDTOs.updateStudentResponse;
import com.studentform.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.Setter;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Students> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Students getStudentById(Long studentId) {
        Students student = studentRepository.findStudentById(studentId);
        System.out.println(student);
        if(student!=null){
            return student;
        }
        return null;
    }

    @Override
    public registerStudentResponse createStudent(registerStudentRequest students) {
        registerStudentResponse response = new registerStudentResponse();
        Students student = new Students();
        student.setId(students.getId());
        student.setFirstName(students.getFirstName());
        student.setLastName(students.getLastName());
        student.setGrade(students.getGrade());
        student.setTargetExam(students.getTargetExam());
        student.setMobileNo(students.getMobileNo());
        response.setStudents(student);
        studentRepository.save(student);
        response.setSuccess(true);
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
        if(studentDetails.getGrade() != null){
            student.setGrade(studentDetails.getGrade());
        }
        if(studentDetails.getTargetExam() != null){
            student.setTargetExam(studentDetails.getTargetExam());
        }
        if(studentDetails.getMobileNo() != null){
            student.setMobileNo(studentDetails.getMobileNo());
        }
        
        studentRepository.save(student);
        response.setStudent(student);
        response.setSuccess(true);
        
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
            response.setMessage("Record Deleted sucesfully.");
        } else {
            response.setDeleted(false);
            response.setMessage("No student found.");
        }
        
        return response;
    }
}
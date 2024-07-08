package com.studentform.backend.repositories;

import java.util.*;
import com.studentform.backend.entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {
    Students findByFirstNameAndLastName(String firstName, String LastName);
    Students findStudentById(Long id);
    Students findStudentByMobileNo(Long mobileNo);
    List<Students> findAll();
}

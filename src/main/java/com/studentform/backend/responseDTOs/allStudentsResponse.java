package com.studentform.backend.responseDTOs;


import com.studentform.backend.entities.Students;
import lombok.Data;
import java.util.*;

@Data
public class allStudentsResponse{
    List<Students> students;
    private boolean success;
    private String message;
}

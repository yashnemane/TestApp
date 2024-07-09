package com.studentform.backend.responseDTOs;

import lombok.Data;

@Data
public class getStudentMarksResponse {
    private Long studentId;
    private Long marks;
    
    public getStudentMarksResponse() {

    }

    public getStudentMarksResponse(Long studentid, Long marks) {
        this.studentId=studentid;
        this.marks=marks;
    }
}

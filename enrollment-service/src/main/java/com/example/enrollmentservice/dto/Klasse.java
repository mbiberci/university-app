package com.example.enrollmentservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonPropertyOrder({"courseName", "instructorName", "capacity", "enrolled", "students"})
public class Klasse {
    private String courseName;
    private String instructorName;
    private List<Student> students = new ArrayList<>();

    public Integer getEnrolled() {
        return students.size();
    }
}

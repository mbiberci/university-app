package com.example.enrollmentservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonPropertyOrder({"studentName", "totalCredit", "courses"})
public class Schedule {
    private String studentName;
    private List<Course> courses = new ArrayList<>();

    public BigDecimal getTotalCredit() {
        return courses.stream().map(Course::getCredit).reduce(BigDecimal.ZERO,BigDecimal::add);
    }
}

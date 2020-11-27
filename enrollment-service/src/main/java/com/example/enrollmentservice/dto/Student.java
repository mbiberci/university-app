package com.example.enrollmentservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
    private String firstName;
    private String lastName;
    private String faculty;

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}

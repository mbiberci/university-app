package com.example.enrollmentservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
    private String firstName;
    private String lastName;
    private String faculty;

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}

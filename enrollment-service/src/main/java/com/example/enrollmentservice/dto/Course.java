package com.example.enrollmentservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {
    private String title;
    private BigDecimal credit;
    private String instructor;
}

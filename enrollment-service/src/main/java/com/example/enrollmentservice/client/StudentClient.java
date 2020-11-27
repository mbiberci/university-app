package com.example.enrollmentservice.client;

import com.example.enrollmentservice.dto.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLConnection;
import java.util.List;

@FeignClient("student-service")
public interface StudentClient {

    @GetMapping("/students/{id}")
    EntityModel<Student> getStudentById(@PathVariable("id") Long id);

    @GetMapping("/students")
    CollectionModel<Student> getStudents();

    @GetMapping("students/search/findByIdIn")
    CollectionModel<Student> getStudentsByIds(@RequestParam("ids") List<Long> ids);
}

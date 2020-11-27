package com.example.enrollmentservice.client;

import com.example.enrollmentservice.dto.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLConnection;
import java.util.List;


@FeignClient("course-service")
public interface CourseClient {

    @GetMapping("/courses/{id}")
    EntityModel<Course> getCourseById(@PathVariable("id") Long id);

    @GetMapping("/courses")
    CollectionModel<Course> getAllCourses();

    @GetMapping("courses/search/findByIdIn")
    CollectionModel<Course> getCoursesByIds(@RequestParam("ids") List<Long> ids);

}

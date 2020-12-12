package com.example.enrollmentservice.controller;


import com.example.enrollmentservice.dto.Klasse;
import com.example.enrollmentservice.dto.Schedule;
import com.example.enrollmentservice.model.Enrollment;
import com.example.enrollmentservice.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping(path = "/schedule/{studentId}")
    public Schedule getScheduleByStudentId(@PathVariable("studentId") Long studentId) {
        return enrollmentService.getScheduleByStudentId(studentId);
    }

    @GetMapping(path = "/class/{courseId}")
    public Klasse getClassByStudentId(@PathVariable("courseId") Long courseId) {
        return enrollmentService.getClassByCourseId(courseId);
    }

    @PostMapping(path = "/enroll")
    public ResponseEntity<Enrollment> enroll(@RequestBody Enrollment enrollment) {
        return ResponseEntity.ok(enrollmentService.enroll(enrollment));
    }

    @DeleteMapping(path = "/drop")
    public void drop(@RequestBody Enrollment enrollment) {
        Example<Enrollment> example = Example.of(enrollment);
        Enrollment toBeDeleted = enrollmentService.findOneByExample(example);
        enrollmentService.delete(toBeDeleted);
    }

}

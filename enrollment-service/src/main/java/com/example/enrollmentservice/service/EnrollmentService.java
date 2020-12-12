package com.example.enrollmentservice.service;

import com.example.enrollmentservice.client.CourseClient;
import com.example.enrollmentservice.client.StudentClient;
import com.example.enrollmentservice.dto.Course;
import com.example.enrollmentservice.dto.Klasse;
import com.example.enrollmentservice.dto.Schedule;
import com.example.enrollmentservice.dto.Student;
import com.example.enrollmentservice.model.Enrollment;
import com.example.enrollmentservice.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final CourseClient courseClient;
    private final StudentClient studentClient;
    private final EnrollmentRepository enrollmentRepository;

    public Schedule getScheduleByStudentId(Long studentId) {
        Student student = studentClient.getStudentById(studentId).getContent();
        List<Long> courseIds = enrollmentRepository.findByStudentId(studentId).stream()
                .map(Enrollment::getCourseId).collect(Collectors.toList());
        Collection<Course> courses = courseClient.getCoursesByIds(courseIds).getContent();
        Schedule schedule = new Schedule();
        schedule.setStudentName(student.getFullName());
        schedule.setCourses(new ArrayList<>(courses));
        return schedule;
    }

    public Klasse getClassByCourseId(Long courseId) {
        Course course = courseClient.getCourseById(courseId).getContent();
        List<Long> studentIds = enrollmentRepository.findByCourseId(courseId).stream()
                .map(Enrollment::getStudentId).collect(Collectors.toList());
        Collection<Student> students = studentClient.getStudentsByIds(studentIds).getContent();
        Klasse klasse = new Klasse();
        klasse.setCourseName(course.getTitle());
        klasse.setInstructorName(course.getInstructor());
        klasse.setStudents(new ArrayList<>(students));
        return klasse;
    }

    public Enrollment enroll(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment findOneByExample(Example<Enrollment> example) {
        return enrollmentRepository.findOne(example).get();
    }

    public void delete(Enrollment enrollment) {
        enrollmentRepository.delete(enrollment);
    }
}

package com.example.enrollmentservice.service;

import com.example.enrollmentservice.client.CourseClient;
import com.example.enrollmentservice.client.StudentClient;
import com.example.enrollmentservice.dto.Course;
import com.example.enrollmentservice.dto.Klasse;
import com.example.enrollmentservice.dto.Schedule;
import com.example.enrollmentservice.dto.Student;
import com.example.enrollmentservice.model.Enrollment;
import com.example.enrollmentservice.repository.EnrollmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {

    @Mock
    private CourseClient courseClient;

    @Mock
    private StudentClient studentClient;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @Test
    void getScheduleByStudentId() {

        Long studentId = 1L;
        Student student = Student.of("John", "Doe", "Engineering");

        List<Course> courses = Arrays.asList(
                Course.of("Data Structures", BigDecimal.valueOf(3), "Tim Cook"),
                Course.of("Software Design", BigDecimal.valueOf(4.5), "Bill Gates")
        );
        List<Long> courseIds = Arrays.asList(11L, 12L);

        List<Enrollment> enrollments = Arrays.asList(
                Enrollment.of(1L, 11L, 1L),
                Enrollment.of(2L, 12L, 1L)
        );

        when(studentClient.getStudentById(any())).thenReturn(EntityModel.of(student));
        when(courseClient.getCoursesByIds(any())).thenReturn(CollectionModel.of(courses));
        when(enrollmentRepository.findByStudentId(any())).thenReturn(enrollments);

        Schedule schedule = enrollmentService.getScheduleByStudentId(studentId);

        verify(studentClient, times(1)).getStudentById(studentId);
        verify(courseClient, times(1)).getCoursesByIds(courseIds);
        verify(enrollmentRepository, times(1)).findByStudentId(studentId);

        assertEquals(student.getFullName(), schedule.getStudentName());
        assertEquals(BigDecimal.valueOf(7.5), schedule.getTotalCredit());
        assertEquals(courses, schedule.getCourses());
    }

    @Test
    void getClassByCourseId() {

        Long courseId = 1L;
        Course course = Course.of("Data Structures", BigDecimal.valueOf(3), "Tim Cook");

        List<Student> students = Arrays.asList(
                Student.of("Adam", "Smith", "Engineering"),
                Student.of("John", "Locke", "Engineering")
        );
        List<Long> studentIds = Arrays.asList(11L, 12L);

        List<Enrollment> enrollments = Arrays.asList(
                Enrollment.of(1L, 1L, 11L),
                Enrollment.of(2L, 1L, 12L)
        );

        when(courseClient.getCourseById(any())).thenReturn(EntityModel.of(course));
        when(studentClient.getStudentsByIds(any())).thenReturn(CollectionModel.of(students));
        when(enrollmentRepository.findByCourseId(any())).thenReturn(enrollments);

        Klasse klasse = enrollmentService.getClassByCourseId(courseId);

        verify(courseClient, times(1)).getCourseById(courseId);
        verify(studentClient, times(1)).getStudentsByIds(studentIds);
        verify(enrollmentRepository, times(1)).findByCourseId(courseId);

        assertEquals(course.getTitle(), klasse.getCourseName());
        assertEquals(course.getInstructor(), klasse.getInstructorName());
        assertEquals(students, klasse.getStudents());
    }

    @Test
    void enroll() {
        Enrollment enrollment = Enrollment.of(1L, 10L, 20L);
        when(enrollmentRepository.save(any())).thenReturn(enrollment);
        Enrollment result = enrollmentService.enroll(enrollment);
        verify(enrollmentRepository, times(1)).save(enrollment);
        assertEquals(enrollment, result);
    }

    @Test
    void findOneByExample() {
        Enrollment enrollment = Enrollment.of(1L, 10L, 20L);
        Example<Enrollment> example = Example.of(enrollment);
        when(enrollmentRepository.findOne(any())).thenReturn(Optional.of(enrollment));
        Enrollment result = enrollmentService.findOneByExample(example);
        verify(enrollmentRepository, times(1)).findOne(example);
        assertEquals(enrollment, result);
    }

    @Test
    void delete() {
        Enrollment enrollment = Enrollment.of(1L, 10L, 20L);
        doNothing().when(enrollmentRepository).delete(any());
        enrollmentService.delete(enrollment);
        verify(enrollmentRepository, times(1)).delete(enrollment);
    }
}
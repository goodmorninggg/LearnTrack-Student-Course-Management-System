package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.time.LocalDate;
import java.util.List;

public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentService studentService,
            CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public void enrollStudent(String studentId, String courseId) throws EntityNotFoundException {
        // Validate existence
        studentService.getStudentById(studentId);
        courseService.getCourseById(courseId);

        String id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId, LocalDate.now(), EnrollmentStatus.ACTIVE);
        enrollmentRepository.add(enrollment);
        System.out.println("Student enrolled successfully. Enrollment ID: " + id);
    }

    public List<Enrollment> getEnrollmentsByStudent(String studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public void updateEnrollmentStatus(String enrollmentId, EnrollmentStatus status) throws EntityNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found with ID: " + enrollmentId));
        enrollment.setStatus(status);
        System.out.println("Enrollment status updated to: " + status);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }
}

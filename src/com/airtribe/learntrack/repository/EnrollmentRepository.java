package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public void add(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollments);
    }

    public List<Enrollment> findByStudentId(String studentId) {
        return enrollments.stream()
                .filter(e -> e.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    public Optional<Enrollment> findById(String id) {
        return enrollments.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }
}

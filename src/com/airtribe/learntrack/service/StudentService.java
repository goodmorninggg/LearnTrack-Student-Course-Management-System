package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.List;

public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(String firstName, String lastName, String email, String batch) {
        String id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch, true);
        repository.add(student);
        System.out.println("Student added successfully with ID: " + id);
    }

    // Overloaded method (simulated for demonstration)
    public void addStudent(Student student) {
        if (student.getId() == null) {
            student.setId(IdGenerator.getNextStudentId());
        }
        repository.add(student);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getStudentById(String id) throws EntityNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
    }

    public void deactivateStudent(String id) throws EntityNotFoundException {
        boolean success = repository.deactivate(id);
        if (!success) {
            throw new EntityNotFoundException("Student not found with ID: " + id);
        }
        System.out.println("Student deactivated successfully.");
    }
}

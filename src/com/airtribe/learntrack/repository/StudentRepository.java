package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public void add(Student student) {
        students.add(student);
    }

    public List<Student> findAll() {
        return new ArrayList<>(students); // Return copy to protect internal list
    }

    public Optional<Student> findById(String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    // Deactivate instead of delete
    public boolean deactivate(String id) {
        Optional<Student> student = findById(id);
        if (student.isPresent()) {
            student.get().setActive(false);
            return true;
        }
        return false;
    }
}

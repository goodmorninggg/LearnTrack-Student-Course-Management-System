package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public void add(Course course) {
        courses.add(course);
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    public Optional<Course> findById(String id) {
        return courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public boolean delete(String id) {
        // Can be a hard delete or logical delete.
        // For Course, let's allow logical delete (deactivate) via update or method.
        // Implementing simple remove for now if needed, but mostly we use active flag.
        return courses.removeIf(c -> c.getId().equals(id));
    }
}

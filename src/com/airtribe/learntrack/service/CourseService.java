package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.List;

public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public void addCourse(String name, String description, int durationInWeeks) {
        String id = IdGenerator.getNextCourseId();
        Course course = new Course(id, name, description, durationInWeeks, true);
        repository.add(course);
        System.out.println("Course added successfully with ID: " + id);
    }

    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    public Course getCourseById(String id) throws EntityNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));
    }

    public void toggleCourseStatus(String id) throws EntityNotFoundException {
        Course course = getCourseById(id);
        course.setActive(!course.isActive());
        System.out.println("Course status updated to: " + (course.isActive() ? "Active" : "Inactive"));
    }
}

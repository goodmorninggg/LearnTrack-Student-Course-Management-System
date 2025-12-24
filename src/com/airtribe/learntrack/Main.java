package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static StudentService studentService;
    private static CourseService courseService;
    private static EnrollmentService enrollmentService;
    private static Scanner scanner;

    public static void main(String[] args) {
        // Initialize dependencies
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        studentService = new StudentService(studentRepository);
        courseService = new CourseService(courseRepository);
        enrollmentService = new EnrollmentService(enrollmentRepository, studentService, courseService);

        scanner = new Scanner(System.in);

        System.out.println("Welcome to LearnTrack - Student & Course Management System");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readIntInput();

            try {
                switch (choice) {
                    case 1:
                        handleStudentManagement();
                        break;
                    case 2:
                        handleCourseManagement();
                        break;
                    case 3:
                        handleEnrollmentManagement();
                        break;
                    case 4:
                        running = false;
                        System.out.println("Exiting LearnTrack. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");
    }

    private static int readIntInput() {
        try {
            int input = Integer.parseInt(scanner.nextLine());
            return input;
        } catch (NumberFormatException e) {
            return -1; // Return invalid choice
        }
    }

    // --- Student Management ---
    private static void handleStudentManagement() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Find Student by ID");
            System.out.println("4. Deactivate Student");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");

            int choice = readIntInput();
            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        findStudentById();
                        break;
                    case 4:
                        deactivateStudent();
                        break;
                    case 5:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }

    private static void addStudent() throws InvalidInputException {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        InputValidator.validateString(firstName, "First Name");

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        InputValidator.validateString(lastName, "Last Name");

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        InputValidator.validateEmail(email);

        System.out.print("Enter Batch: ");
        String batch = scanner.nextLine();

        studentService.addStudent(firstName, lastName, email, batch);
    }

    private static void viewAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private static void findStudentById() throws EntityNotFoundException {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        Student student = studentService.getStudentById(id);
        System.out.println("Student found: " + student);
    }

    private static void deactivateStudent() throws EntityNotFoundException {
        System.out.print("Enter Student ID to deactivate: ");
        String id = scanner.nextLine();
        studentService.deactivateStudent(id);
    }

    // --- Course Management ---
    private static void handleCourseManagement() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Find Course by ID");
            System.out.println("4. Toggle Course Active Status");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");

            int choice = readIntInput();
            try {
                switch (choice) {
                    case 1:
                        addCourse();
                        break;
                    case 2:
                        viewAllCourses();
                        break;
                    case 3:
                        findCourseById();
                        break;
                    case 4:
                        toggleCourseStatus();
                        break;
                    case 5:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }

    private static void addCourse() throws InvalidInputException {
        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine();
        InputValidator.validateString(name, "Course Name");

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Duration (weeks): ");
        int duration = readIntInput();
        if (duration <= 0) {
            throw new InvalidInputException("Duration must be positive.");
        }

        courseService.addCourse(name, description, duration);
    }

    private static void viewAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            courses.forEach(System.out::println);
        }
    }

    private static void findCourseById() throws EntityNotFoundException {
        System.out.print("Enter Course ID: ");
        String id = scanner.nextLine();
        Course course = courseService.getCourseById(id);
        System.out.println("Course found: " + course);
    }

    private static void toggleCourseStatus() throws EntityNotFoundException {
        System.out.print("Enter Course ID: ");
        String id = scanner.nextLine();
        courseService.toggleCourseStatus(id);
    }

    // --- Enrollment Management ---
    private static void handleEnrollmentManagement() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Enrollment Management ---");
            System.out.println("1. Enroll Student");
            System.out.println("2. View All Enrollments");
            System.out.println("3. View Enrollments by Student");
            System.out.println("4. Update Enrollment Status");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");

            int choice = readIntInput();
            try {
                switch (choice) {
                    case 1:
                        enrollStudent();
                        break;
                    case 2:
                        viewAllEnrollments();
                        break;
                    case 3:
                        viewEnrollmentsByStudent();
                        break;
                    case 4:
                        updateEnrollmentStatus();
                        break;
                    case 5:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }

    private static void enrollStudent() throws EntityNotFoundException {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();

        enrollmentService.enrollStudent(studentId, courseId);
    }

    private static void viewAllEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
        } else {
            enrollments.forEach(System.out::println);
        }
    }

    private static void viewEnrollmentsByStudent() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for this student.");
        } else {
            enrollments.forEach(System.out::println);
        }
    }

    private static void updateEnrollmentStatus() throws EntityNotFoundException, InvalidInputException {
        System.out.print("Enter Enrollment ID: ");
        String id = scanner.nextLine();

        System.out.println("Choose new status:");
        System.out.println("1. ACTIVE");
        System.out.println("2. COMPLETED");
        System.out.println("3. CANCELLED");
        int choice = readIntInput();

        EnrollmentStatus status;
        switch (choice) {
            case 1:
                status = EnrollmentStatus.ACTIVE;
                break;
            case 2:
                status = EnrollmentStatus.COMPLETED;
                break;
            case 3:
                status = EnrollmentStatus.CANCELLED;
                break;
            default:
                throw new InvalidInputException("Invalid status choice.");
        }

        enrollmentService.updateEnrollmentStatus(id, status);
    }
}

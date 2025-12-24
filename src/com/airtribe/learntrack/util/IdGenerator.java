package com.airtribe.learntrack.util;

public class IdGenerator {
    private static int studentIdCounter = 1000;
    private static int courseIdCounter = 500;
    private static int enrollmentIdCounter = 1;

    public static String getNextStudentId() {
        return "S" + (++studentIdCounter);
    }

    public static String getNextCourseId() {
        return "C" + (++courseIdCounter);
    }

    public static String getNextEnrollmentId() {
        return "E" + (++enrollmentIdCounter);
    }
}

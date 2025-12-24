package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

public class InputValidator {
    public static void validateString(String input, String fieldName) throws InvalidInputException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }

    public static void validateEmail(String email) throws InvalidInputException {
        validateString(email, "Email");
        if (!email.contains("@") || !email.contains(".")) {
            throw new InvalidInputException("Invalid email format.");
        }
    }
}

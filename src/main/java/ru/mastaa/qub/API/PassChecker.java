package ru.mastaa.qub.API;

import org.checkerframework.checker.units.qual.C;

public class PassChecker {
    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        String specialCharacters = "!@#$%^&*()-+";

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (specialCharacters.indexOf(c) != -1) {
                hasSpecialChar = true;
            }
        }

        if (!hasLowerCase || !hasUpperCase || !hasDigit || !hasSpecialChar) {
            return false;
        }

        for (int i = 0; i < password.length() - 1; i++) {
            if (password.charAt(i) == password.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
}

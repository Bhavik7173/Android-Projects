package com.gi.application2.staticfunction;

import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Pattern;

public class Validation {

    public static String namePattern = "^[A-Za-z]\\w{5, 29}$";
    public static String namePattern1 = "^[a-zA-Z ]+$";
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");

    public static boolean passwordValidator(EditText password) {
        String passwordInput = password.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    public static boolean emailValidator(EditText etMail) {

        String emailToText = etMail.getText().toString();

        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            etMail.setError(null);
            return true;
        }
        etMail.setError("Invalid Email address !");

        return false;
    }

    //    public static boolean nameValidation(String edFname) {
//        if (edFname.equals(namePattern)) {
////            edFname.setError("valid Name");
//            if (edFname.length() > 0) {
//                return true;
//            }
//        }
////        edFname.setError("invalid Name.Please Enter the more than 3 Character!");
//        return false;
//
//    }
    public static boolean nameValidation(EditText edFname) {
        if (edFname.equals(namePattern)) {
//            edFname.setError("valid Name");
            if (edFname.length() > 0) {
                return true;
            }
        }
//        edFname.setError("invalid Name.Please Enter the more than 3 Character!");
        return false;

    }
}

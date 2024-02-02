package gov.iti.jets.Controllers.services;

import java.util.regex.Pattern;

public class FieldsValidator {
    public static boolean isValidEmail(String email) {
        if (email.isEmpty()) {
            CustomDialogs.showErrorDialog("You can't leave Email field empty !!");
            return false;
        } else {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pat = Pattern.compile(emailRegex);
            if (pat.matcher(email).matches()) {
                return true;
            } else {
                CustomDialogs.showErrorDialog("This email is not valid !!");
                return false;
            }
        }
    }

    public static boolean isValidPassword(String password) {
        if (password.isEmpty()) {
            CustomDialogs.showErrorDialog("You can't leave Password field empty !!");
            return false;
        } else if (password.length() >= 8) {
            return true;
        } else {
            CustomDialogs.showErrorDialog("Password must be 8 Character or Higher !!");
            return false;
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            CustomDialogs.showErrorDialog("You can't leave Phone Number field empty !!");
            return false;
        } else {
            boolean isValid = true;
            char[] phoneInCharArray = phoneNumber.toCharArray();
            for (char digit : phoneInCharArray) {
                if (!Character.isDigit(digit)) {
                    CustomDialogs.showErrorDialog("This is Not valid Phone Number !!");
                    isValid = false;
                    break;
                }
            }
            return isValid;
        }
    }

    public static boolean isValidName(String name) {
        if (name.length() > 3) {
            return true;
        }
        CustomDialogs.showErrorDialog("This is not valid Name !!");
        return false;
    }

    public static boolean isValidCountry(String country) {
        if (country.length() > 4) {
            return true;
        }
        CustomDialogs.showErrorDialog("This is not valid Country Name");
        return false;
    }

}

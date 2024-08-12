package com.userregistration.service.validation;

import com.userregistration.exception.GeneralValidationException;
import com.userregistration.exception.InvalidEmailException;
import com.userregistration.exception.InvalidPasswordException;
import com.userregistration.model.User;
import com.userregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidation {

   @Autowired
   private UserRepository userRepository;


   //------------------------------------------------------------------------------------------------------------------
   //Registration validation.
    public List<String> validateUserWhileRegistration(User user) {
        List<String> errors = new ArrayList<>();

        if (user.getFirstName() == null || user.getFirstName().length() < 3) {
            errors.add("First name must be at least 3 characters long.");
        }

        if (user.getLastName() == null || user.getLastName().length() < 3) {
            errors.add("Last name must be at least 3 characters long.");
        }

        if (user.getCountryCode() == null || !user.getCountryCode().matches("\\+[1-9][0-9]{0,2}")) {
            errors.add("Country code must start with '+' and be followed by 1-3 digits.");
        }

//        String mobileNumber = user.getMobileNumber();
//        if (mobileNumber == null || mobileNumber.startsWith("0") || !mobileNumber.matches("^[1-9]\\d{9}$")) {
//            errors.add("Invalid mobile number.");
//        }

        String mobileNumber = user.getMobileNumber();
        if (mobileNumber == null) {
            errors.add("Mobile number must not be null");
        } else if (mobileNumber.startsWith("0")) {
            errors.add("Mobile number should not start with 0");
        } else if (!mobileNumber.matches("^[1-9]\\d{9}$")) {
            errors.add("Mobile number is not valid !");
        }

        String email = user.getEmail();
        if (email == null || email.isEmpty()) {
            errors.add("Email must not be null or empty.");
        } else if (!email.matches("^[A-Za-z0-9]+@[A-Za-z]+\\.[A-Za-z]{2,}$")) {
            errors.add("Invalid email format.");
        }

        if (user.getDateOfBirth() == null || !user.getDateOfBirth().matches("\\d{4}-\\d{2}-\\d{2}")) {
            errors.add("Date of Birth must be in 'YYYY-MM-DD' format.");
        }

        if (user.getAddress() == null || user.getAddress().length() < 3) {
            errors.add("Address must be at least 3 characters long.");
        }

        if (user.getCity() == null || user.getCity().length() < 2) {
            errors.add("City must be at least 2 characters long.");
        }

        if (user.getState() == null || user.getState().length() < 2) {
            errors.add("State must be at least 2 characters long.");
        }

        if (user.getCountry() == null || user.getCountry().length() < 2) {
            errors.add("Country must be at least 2 characters long.");
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            errors.add("Password must be at least 6 characters long.");
        }

        return errors;
    }


//---------------------------------------------------------------------------------------------------------------------

    //login validation.
    private boolean isValidPassword(String email, String password) {
        // Placeholder for actual password validation logic
        return userRepository.checkPassword(email, password);
    }

    public void validateLogin(String email, String password) {
        if (email == null || email.isEmpty()) {
            throw new InvalidEmailException("Email must not be null or empty.");
        } else if (!email.matches("^[A-Za-z0-9]+@[A-Za-z]+\\.[A-Za-z]{2,}$")) {
            throw new InvalidEmailException("Invalid email format.");
        }
        if (!userRepository.existsByEmail(email)) {
            throw new InvalidEmailException("Email does not exist.");
        }

        if (password == null || password.length() < 6) {
            throw new InvalidPasswordException("Password must be at least 6 characters long.");
        }

        if (!isValidPassword(email, password)) {
            throw new InvalidPasswordException("Invalid password.");
        }
    }


    //--------------------------------------------------------------------------------------------------------

    //update validation.
    public void validateUpdate(User user) throws GeneralValidationException {
        List<String> errors = new ArrayList<>();

        if (user.getFirstName() != null && user.getFirstName().length() < 3) {
            errors.add("First name must be at least 3 characters long");
        }
        if (user.getLastName() != null && user.getLastName().length() < 3) {
            errors.add("Last name must be at least 3 characters long");
        }
        if (user.getCountryCode() != null && !user.getCountryCode().matches("\\+[1-9][0-9]{0,2}")) {
            errors.add("Country code must start with '+' and be followed by 1-3 digits");
        }
        String mobileNumber = user.getMobileNumber();
        if (mobileNumber != null && (mobileNumber.startsWith("0") || !mobileNumber.matches("^[1-9]\\d{9}$"))) {
            errors.add("Invalid mobile number");
        }
        if (user.getEmail() != null && !user.getEmail().matches("^[A-Za-z0-9]+@[A-Za-z]+\\.[A-Za-z]{2,}$")) {
            errors.add("Invalid email format");
        }
        if (user.getDateOfBirth() != null && !user.getDateOfBirth().matches("\\d{4}-\\d{2}-\\d{2}")) {
            errors.add("Date of Birth must be in 'YYYY-MM-DD' format");
        }
        if (user.getAddress() != null && user.getAddress().length() < 3) {
            errors.add("Address must be at least 3 characters long");
        }
        if (user.getCity() != null && user.getCity().length() < 2) {
            errors.add("City must be at least 2 characters long");
        }
        if (user.getState() != null && user.getState().length() < 2) {
            errors.add("State must be at least 2 characters long");
        }
        if (user.getCountry() != null && user.getCountry().length() < 2) {
            errors.add("Country must be at least 2 characters long");
        }
        if (user.getPassword() != null && user.getPassword().length() < 6) {
            errors.add("Password must be at least 6 characters long");
        }

        if (!errors.isEmpty()) {
            throw new GeneralValidationException(errors);
        }
    }
}

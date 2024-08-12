package com.userregistration.service;

import com.userregistration.exception.*;
import com.userregistration.model.User;
import com.userregistration.repository.UserRepository;
import com.userregistration.service.updation.UserUpdation;
import com.userregistration.service.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final MongoTemplate mongoTemplate;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidation userValidation;
    @Autowired
    private UserUpdation userUpdation;

    public UserService(MongoTemplate mongoTemplate, UserRepository userRepository) {
        this.mongoTemplate = mongoTemplate;
        this.userRepository = userRepository;
    }



    //1.user registration
    public User registerUser(User user) {
        List<String> validationErrors = userValidation.validateUserWhileRegistration(user);

        if (!validationErrors.isEmpty()) {
            throw new GeneralValidationException(validationErrors);
        }

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email is already used");
        }
        // Seting   the active status.
        user.setActive(true);
        //Save the user to the database.
        return userRepository.save(user);
    }


    //2.login api
    public User loginUser(String email, String password) {
         userValidation.validateLogin(email, password);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidEmailException("Email does not exist."));

        if (!user.getActive()) {
            throw new InvalidEmailException("User is deactivated.");
        }
        return user;
    }


    //Update user details
    public User updateUser(String email, User userUpdate) throws InvalidEmailException, GeneralValidationException {
        // Perform validation before updating the user
        userValidation.validateUpdate(userUpdate);
        //return updated user.
        return userUpdation.updateUser(email,userUpdate);
    }


    //Deletion of user (setting active status as false)
    public void deleteUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new InvalidEmailException("User with the given email does not exist.");
        }
        // Update only the 'active' field to false
        Query query = new Query(Criteria.where("email").is(email));
        Update update = new Update().set("active", false);

        mongoTemplate.updateFirst(query, update, User.class);
    }


    // Get user by email
    public User getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new InvalidEmailException("User with the given email does not exist.");
        }
        return userOptional.get();
    }


    // Method to get all users
    public List<User> getAllUsers() {
        List list =  userRepository.findAll();
        if(list.isEmpty()){
            throw new UserListIsEmptyException("No users found.");
        }
        return list;
    }

}
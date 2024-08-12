package com.userregistration.controller;

import com.userregistration.exception.*;
import com.userregistration.model.ApiResponse;
import com.userregistration.model.User;
import com.userregistration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //user registration
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody User user) {
            User registeredUser = userService.registerUser(user);
            ApiResponse response = new ApiResponse(true, "Registration successful !", HttpStatus.CREATED.value(), registeredUser,new Date());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //user login
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestParam String email, @RequestParam String password) {
            User user = userService.loginUser(email, password);
            ApiResponse response = new ApiResponse(true, "Login successful", HttpStatus.OK.value(), null,new Date());
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //user updation
    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUser(@RequestParam String email, @RequestBody User updatedUser) {
            User user = userService.updateUser(email, updatedUser);
            ApiResponse response = new ApiResponse(true, "User updated successfully", HttpStatus.OK.value(), user,new Date());
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //User deletion using email
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String email) {
            userService.deleteUser(email);
            ApiResponse response = new ApiResponse(true, "User has been deactivated successfully.", HttpStatus.OK.value(), null,new Date());
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //get user by email
    @PostMapping("/by-email")
    public ResponseEntity<ApiResponse> getUserByEmail(@RequestParam("email") String email) {
            User user = userService.getUserByEmail(email);
            ApiResponse response = new ApiResponse(true, "User retrieved successfully", HttpStatus.OK.value(), user,new Date());
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API to get all users
    @PostMapping("/get-all-users")
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            throw new UserListIsEmptyException("No users found in the database.");
        }
        ApiResponse response = new ApiResponse(true, "Users fetched successfully", HttpStatus.OK.value(), users,new Date());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

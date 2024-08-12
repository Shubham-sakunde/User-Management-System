package com.userregistration.controller;

import com.userregistration.exception.*;
import com.userregistration.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(GeneralValidationException.class)
    public ResponseEntity<ApiError> handleGeneralValidationException(GeneralValidationException ex) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getErrors(), new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        ApiError error = new ApiError(HttpStatus.CONFLICT.value(), List.of(ex.getMessage()), new Date());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ApiError> handleInvalidEmailException(InvalidEmailException ex){
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), List.of(ex.getMessage()),new Date());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiError> handleInvalidPasswordException(InvalidPasswordException ex){
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(),List.of(ex.getMessage()),new Date());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserListIsEmptyException.class)
    public ResponseEntity<ApiError> handleUserListIsEmptyException(UserListIsEmptyException ex){
        ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), List.of(ex.getMessage()),new Date());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }


}
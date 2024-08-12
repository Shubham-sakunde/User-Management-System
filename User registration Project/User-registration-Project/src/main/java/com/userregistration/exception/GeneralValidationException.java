package com.userregistration.exception;

import java.util.List;

public class GeneralValidationException extends RuntimeException {
    private final List<String> errors;



    public GeneralValidationException(List<String> errors) {
        super("General validation failed");
        this.errors = errors;
    }



    public List<String> getErrors() {
        return errors;
    }



}
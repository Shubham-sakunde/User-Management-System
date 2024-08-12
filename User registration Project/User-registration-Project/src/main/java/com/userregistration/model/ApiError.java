package com.userregistration.model;

import java.util.Date;
import java.util.List;

public class ApiError {
    private int status;
    private List<String> errors;
    private Date date;

    public ApiError(int status, List<String> errors, Date date) {
        this.status = status;
        this.errors = errors;
        this.date = date;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date timestamp) {
        this.date = timestamp;
    }
}
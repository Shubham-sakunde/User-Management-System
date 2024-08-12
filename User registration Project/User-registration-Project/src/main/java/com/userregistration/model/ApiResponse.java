package com.userregistration.model;

import java.util.Date;

public class ApiResponse {
    private boolean success;
    private Object message;
    private int status;
    private Object data;
    private Date date;

    public ApiResponse(boolean success, Object message, int status, Object data,Date date) {
        this.success = success;
        this.message = message;
        this.status = status;
        this.data = data;
        this.date = date;

    }

    // Getters and Setters
    public ApiResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

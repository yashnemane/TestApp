package com.studentform.backend.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    private String message;
    private int StatusCode;
    private boolean status;

    public CustomException() {
        super();
    }

    public CustomException(String message, int StatusCode) {
        super(message);
        this.message = message;
        this.StatusCode = StatusCode;
        this.status = false;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "message='" + message + '\'' +
                ", StatusCode=" + StatusCode +
                '}';
    }
}

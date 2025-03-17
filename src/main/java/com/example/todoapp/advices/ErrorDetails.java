package com.example.todoapp.advices;

public class ErrorDetails {

    private int status;
    private String message;

    public ErrorDetails(int status, String message) {
        this.status = status;
        this.message = message;
    }


    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

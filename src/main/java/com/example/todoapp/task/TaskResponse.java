package com.example.todoapp.task;

public class TaskResponse {
    private int responseStatusCode;
    private String responseMessage;
    private Task responseTask;

    public TaskResponse(int responseStatusCode, String responseMessage, Task responseTask) {
        this.responseStatusCode = responseStatusCode;
        this.responseMessage = responseMessage;
        this.responseTask = responseTask;
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public Task getResponseTask() {
        return responseTask;
    }
}

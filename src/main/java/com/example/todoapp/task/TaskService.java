package com.example.todoapp.task;

public interface TaskService {

    void addTask(Task task);

    Task getTaskById(String id);

    int deleteTask(Task task);

}

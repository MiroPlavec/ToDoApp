package com.example.todoapp.task;

public interface TaskService {

    void addTask(Task task);

    Task getTaskById(String id);

    int deleteTask(String id);

    int updateTask(Task task);

    Iterable<Task> getAllTasks();

}

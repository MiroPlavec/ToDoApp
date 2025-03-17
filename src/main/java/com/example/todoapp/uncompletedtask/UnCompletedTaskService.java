package com.example.todoapp.uncompletedtask;

import com.example.todoapp.task.Task;

import java.util.List;

public interface UnCompletedTaskService {

    List<Task> addTask(Task task);

    List<Task> deleteTask(String id);

    List<Task> updateTask(Task task);

    List<Task> completeTask(String id);

    List<Task> getUncompletedTasks();

}

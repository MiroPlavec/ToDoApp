package com.example.todoapp.completedtask;

import com.example.todoapp.task.Task;

import java.util.List;


public interface CompletedTaskService {

    List<Task> getCompetedTasks();

    List<Task> removeCompletedTasks(String taskId);
}

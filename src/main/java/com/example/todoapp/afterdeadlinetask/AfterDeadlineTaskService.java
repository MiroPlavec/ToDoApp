package com.example.todoapp.afterdeadlinetask;

import com.example.todoapp.task.Task;

import java.util.List;

public interface AfterDeadlineTaskService {

    List<Task> getAfterDeadlineTasks();

    List<Task> removeAfterDeadlineTasks(String id);
}

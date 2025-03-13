package com.example.todoapp.completedtask;

import com.example.todoapp.task.Task;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CompletedTaskServiceImpl implements CompletedTaskService{

    private final CompletedTaskRepository completedTaskRepository;

    public CompletedTaskServiceImpl(CompletedTaskRepository completedTaskRepository) {
        this.completedTaskRepository = completedTaskRepository;
    }

    @Override
    public List<Task> getCompetedTasks() {
        List<Task> completedTasks = completedTaskRepository.findAllCompletedTasks();
        sortTaskList(completedTasks);
        return completedTasks;
    }

    @Override
    public List<Task> removeCompletedTasks(String taskId) {
        completedTaskRepository.deleteCompletedTask(taskId);
        return getCompetedTasks();
    }

    private void sortTaskList(List<Task> tasks){
        Collections.sort(tasks);
    }
}

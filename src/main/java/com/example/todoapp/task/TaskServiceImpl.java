package com.example.todoapp.task;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTask(Task task) {
        taskRepository.save(task.getTitle(), task.getDescription(), task.isCompleted(), task.getDeadlineDate(), task.getCreationDate());
    }

    @Override
    public Task getTaskById(String id) {
        int parsedId = parseId(id);
        Task task = taskRepository.findById(parsedId);
        return task;
    }

    @Override
    public int deleteTask(String id) {
        int parsedId = parseId(id);
        int response = taskRepository.delete(parsedId);
        if(response == 0) throw new TaskNotFoundException("Task with id=%d was not found".formatted(parsedId));
        return response;
    }

    @Override
    public int updateTask(Task task){
        int response = taskRepository.update(task.getTitle(), task.getDescription(), task.isCompleted(), task.getDeadlineDate(), task.getCreationDate(), task.getTaskId());
        if(response == 0) throw new TaskNotFoundException("Nothing to update. Task with id=%d was not found.".formatted(task.getTaskId()));
        return response;
    }

    @Override
    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public int completeTask(String id) {
        int parsedId = parseId(id);
        int result = taskRepository.completeTask(parsedId);

        return result;
    }

    @Override
    public Iterable<Task> getCompletedTasks() {
        return taskRepository.findCompletedTask();
    }

    @Override
    public Iterable<Task> getUncompletedTasks() {
        return taskRepository.findUncompletedTasks();
    }

    @Override
    public void sort(Iterable<Task> tasks){
        Collections.sort((List) tasks);
    }

    private int parseId(String id){
        try {
            return Integer.parseInt(id);
        }catch (NumberFormatException e){
            throw new TaskIdParseException("Task with id=%s can not be parsed to Integer".formatted(id));
        }
    }



}

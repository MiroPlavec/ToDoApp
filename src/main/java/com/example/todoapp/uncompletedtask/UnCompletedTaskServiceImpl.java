package com.example.todoapp.uncompletedtask;

import com.example.todoapp.task.Task;
import com.example.todoapp.task.TaskIdParseException;
import com.example.todoapp.task.TaskNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UnCompletedTaskServiceImpl implements UnCompletedTaskService {

    private final UnCompletedTaskRepository taskRepository;

    public UnCompletedTaskServiceImpl(UnCompletedTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> addTask(Task task) {
        taskRepository.save(task.getTitle(), task.getDescription(), task.isCompleted(), task.isAfterDeadline(), task.getDeadlineDate());
        return getUncompletedTasks();
    }

    @Override
    public List<Task> deleteTask(String id) {
        int parsedId = parseId(id);
        int response = taskRepository.delete(parsedId);
        if(response == 0) throw new TaskNotFoundException("Task with id=%d was not found".formatted(parsedId));
        return getUncompletedTasks();
    }

    @Override
    public List<Task> updateTask(Task task){
        int response = taskRepository.update(task.getTitle(), task.getDescription(), task.isCompleted(), task.isAfterDeadline(), task.getDeadlineDate(), task.getTaskId());
        if(response == 0) throw new TaskNotFoundException("Nothing to update. Task with id=%d was not found.".formatted(task.getTaskId()));
        return getUncompletedTasks();
    }

    @Override
    public List<Task> completeTask(String id) {
        int parsedId = parseId(id);
        int result = taskRepository.completeTask(parsedId);

        return getUncompletedTasks();
    }

    @Override
    public List<Task> getUncompletedTasks() {
        List<Task> uncompletedTasks = taskRepository.findUncompletedTasks();
        Collections.sort(uncompletedTasks);
        return uncompletedTasks;
    }


    private int parseId(String id){
        try {
            return Integer.parseInt(id);
        }catch (NumberFormatException e){
            throw new TaskIdParseException("Task with id=%s can not be parsed to Integer".formatted(id));
        }
    }



}

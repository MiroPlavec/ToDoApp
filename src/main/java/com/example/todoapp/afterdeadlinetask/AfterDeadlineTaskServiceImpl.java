package com.example.todoapp.afterdeadlinetask;

import com.example.todoapp.task.Task;
import com.example.todoapp.task.TaskIdParseException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AfterDeadlineTaskServiceImpl implements AfterDeadlineTaskService{

    private final AfterDeadlineTaskRepository deadlineTaskRepository;

    public AfterDeadlineTaskServiceImpl(AfterDeadlineTaskRepository deadlineTaskRepository) {
        this.deadlineTaskRepository = deadlineTaskRepository;
    }

    @Override
    public List<Task> getAfterDeadlineTasks() {
        List<Task> afterDeadlineTasks = deadlineTaskRepository.findAll();
        Collections.sort(afterDeadlineTasks);
        return afterDeadlineTasks;
    }

    @Override
    public List<Task> removeAfterDeadlineTasks(String id) {
        int parsedId = parseId(id);
        deadlineTaskRepository.deleteTask(parsedId);
        return getAfterDeadlineTasks();
    }

    private int parseId(String id){
        try {
            return Integer.parseInt(id);
        }catch (NumberFormatException e){
            throw new TaskIdParseException("Task with id=%s can not be parsed to Integer".formatted(id));
        }
    }
}

package com.example.todoapp.task;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTask(Task task) {
        taskRepository.save(task.getTitle(), task.getDescription(), task.isCompleted(), task.isAfterDeadline(), task.getDeadlineDate());
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
        int response = taskRepository.update(task.getTitle(), task.getDescription(), task.isCompleted(), task.isAfterDeadline(), task.getDeadlineDate(), task.getTaskId());
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

    // first before deadline, than after deadline;
    @Override
    public void sort(Iterable<Task> tasks){
        List<Task> afterDeadlineTasks = new ArrayList<>();
        List<Task> beforeDeadlineTasks = (List<Task>) tasks;

        Iterator<Task> taskIterator = beforeDeadlineTasks.iterator();
        while (taskIterator.hasNext()){
            Task task = taskIterator.next();
            if(task.isAfterDeadline()){
                afterDeadlineTasks.add(task);
                taskIterator.remove();
            }
        }

        Comparator<Task> comparator = (t1, t2) -> {
            if(t1.getDeadlineDate().isEqual(t2.getDeadlineDate())) return 0;
            if(t1.getDeadlineDate().isAfter(t2.getDeadlineDate())) return 1;
            return -1;
        };
        afterDeadlineTasks.sort(comparator);
        beforeDeadlineTasks.sort(comparator);

        beforeDeadlineTasks.addAll(afterDeadlineTasks);
    }

    private int parseId(String id){
        try {
            return Integer.parseInt(id);
        }catch (NumberFormatException e){
            throw new TaskIdParseException("Task with id=%s can not be parsed to Integer".formatted(id));
        }
    }



}

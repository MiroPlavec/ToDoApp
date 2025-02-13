package com.example.todoapp.task;

import org.springframework.stereotype.Service;

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
        int parsedId;
        try {
            parsedId = Integer.parseInt(id);
        }catch (NumberFormatException e){
            throw new TaskIdParseException("Task id: \"%s\" can not be parsed to Integer".formatted(id));
        }
        Task task = taskRepository.findById(parsedId);
        return task;
    }

    @Override
    public int deleteTask(Task task) {
        return 0;
    }


}

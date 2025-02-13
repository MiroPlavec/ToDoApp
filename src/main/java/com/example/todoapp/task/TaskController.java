package com.example.todoapp.task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String id){
        Task task = taskService.getTaskById(id);

        return ResponseEntity.ok(task);
    }

    @PostMapping
    public void addTask(@RequestBody Task task){
        taskService.addTask(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable String id){
        int response = taskService.deleteTask(id);
        TaskResponse taskResponse = new TaskResponse(HttpStatus.OK.value(), "Task with id=%s was successfully removed".formatted(id), null);
        return ResponseEntity.ok(taskResponse);
    }

    @PutMapping
    public ResponseEntity<TaskResponse> updateTask(@RequestBody Task task){
        int response = taskService.updateTask(task);
        TaskResponse taskResponse = new TaskResponse(HttpStatus.OK.value(), "Task with id=%s was successfully updated".formatted(task.getTaskId()), null);
        return ResponseEntity.ok(taskResponse);
    }

}

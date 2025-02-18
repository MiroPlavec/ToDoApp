package com.example.todoapp.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String home(Model model){
        Iterable<Task> uncompletedTasks = taskService.getUncompletedTasks();
        model.addAttribute("tasks", uncompletedTasks);
        return "index.html";
    }

    @PostMapping
    public String add(@RequestBody Task task, Model model){
        taskService.addTask(task);
        Iterable<Task> uncompletedTasks = taskService.getUncompletedTasks();
        model.addAttribute("tasks", uncompletedTasks);
        return "index.html";
    }


    @PutMapping()
    public String completeTask(@RequestBody Task task, Model model){
        taskService.updateTask(task);
        Iterable<Task> uncompletedTasks = taskService.getUncompletedTasks();
        model.addAttribute("tasks", uncompletedTasks);
        return "index.html";
    }

//    @GetMapping
//    public ResponseEntity<Iterable<Task>> getAllTasks(){
//        return ResponseEntity.ok(taskService.getAllTasks());
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Task> getTask(@PathVariable String id){
//        Task task = taskService.getTaskById(id);
//
//        return ResponseEntity.ok(task);
//    }
//
//    @PostMapping
//    public void addTask(@RequestBody Task task){
//        taskService.addTask(task);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<TaskResponse> deleteTask(@PathVariable String id){
//        int response = taskService.deleteTask(id);
//        TaskResponse taskResponse = new TaskResponse(HttpStatus.OK.value(), "Task with id=%s was successfully removed".formatted(id), null);
//        return ResponseEntity.ok(taskResponse);
//    }
//
//    @PutMapping
//    public ResponseEntity<TaskResponse> updateTask(@RequestBody Task task){
//        int response = taskService.updateTask(task);
//        TaskResponse taskResponse = new TaskResponse(HttpStatus.OK.value(), "Task with id=%s was successfully updated".formatted(task.getTaskId()), null);
//        return ResponseEntity.ok(taskResponse);
//    }

}

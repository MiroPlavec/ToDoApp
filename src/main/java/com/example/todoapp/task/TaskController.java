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
        taskService.sort(uncompletedTasks);

        model.addAttribute("tasks", uncompletedTasks);
        return "index.html";
    }

    @PostMapping
    public String add(@RequestBody Task task, Model model){
        taskService.addTask(task);
        Iterable<Task> uncompletedTasks = taskService.getUncompletedTasks();
        taskService.sort(uncompletedTasks);

        model.addAttribute("tasks", uncompletedTasks);
        return "index.html";
    }


    @PutMapping
    public String editTask(@RequestBody Task task, Model model){
        taskService.updateTask(task);
        Iterable<Task> uncompletedTasks = taskService.getUncompletedTasks();
        taskService.sort(uncompletedTasks);

        model.addAttribute("tasks", uncompletedTasks);
        return "index.html";
    }

    @PutMapping("/{taskId}")
    public String completeTask(@PathVariable String taskId, Model model){
        taskService.completeTask(taskId);
        Iterable<Task> uncompletedTasks = taskService.getUncompletedTasks();
        taskService.sort(uncompletedTasks);

        model.addAttribute("tasks", uncompletedTasks);
        return "index.html";
    }

}

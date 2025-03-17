package com.example.todoapp.uncompletedtask;

import com.example.todoapp.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/uncomptasks")
public class UnCompletedTaskController {

    private final UnCompletedTaskService taskService;

    public UnCompletedTaskController(UnCompletedTaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getUncompletedTasks(Model model){
        List<Task> uncompletedTasks = taskService.getUncompletedTasks();

        model.addAttribute("tasks", uncompletedTasks);
        return "uncomptasks.html";
    }

    @PostMapping
    public String addTask(@RequestBody Task task, Model model){
        List<Task> uncompletedTasks = taskService.addTask(task);

        model.addAttribute("tasks", uncompletedTasks);
        return "uncomptasks.html";
    }


    @PutMapping
    public String editTask(@RequestBody Task task, Model model){
        List<Task> uncompletedTasks = taskService.updateTask(task);

        model.addAttribute("tasks", uncompletedTasks);
        return "uncomptasks.html";
    }

    @PutMapping("/{taskId}")
    public String completeTask(@PathVariable String taskId, Model model){
        List<Task> uncompletedTasks = taskService.completeTask(taskId);

        model.addAttribute("tasks", uncompletedTasks);
        return "uncomptasks.html";
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable String taskId, Model model){
        List<Task> uncompletedTasks = taskService.deleteTask(taskId);

        model.addAttribute("tasks", uncompletedTasks);
        return "uncomptasks.html";
    }

}

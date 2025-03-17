package com.example.todoapp.completedtask;

import com.example.todoapp.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/comptasks")
public class CompletedTaskController {

    private final CompletedTaskService completedTaskService;

    public CompletedTaskController(CompletedTaskServiceImpl completedTaskService) {
        this.completedTaskService = completedTaskService;
    }

    @GetMapping
    public String getCompletedTasks(Model model){
        List<Task> completedTasks = completedTaskService.getCompetedTasks();
        model.addAttribute("tasks", completedTasks);

        return "comptasks.html";
    }

    @DeleteMapping("/{taskId}")
    public String deleteCompletedTask(@PathVariable String taskId, Model model){
        List<Task> completedTasks = completedTaskService.removeCompletedTasks(taskId);
        model.addAttribute("tasks", completedTasks);
        return "comptasks.html";
    }
}

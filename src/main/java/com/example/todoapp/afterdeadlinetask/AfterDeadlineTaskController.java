package com.example.todoapp.afterdeadlinetask;

import com.example.todoapp.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/afterdeadline")
public class AfterDeadlineTaskController {

    private final AfterDeadlineTaskService afterDeadlineTaskService;

    public AfterDeadlineTaskController(AfterDeadlineTaskService afterDeadlineTaskService) {
        this.afterDeadlineTaskService = afterDeadlineTaskService;
    }

    @GetMapping
    public String getAfterDeadlineTasks(Model model){
        List<Task> afterDeadlineTasks = afterDeadlineTaskService.getAfterDeadlineTasks();
        model.addAttribute("tasks", afterDeadlineTasks);
        return "afterdeadlinetasks.html";
    }

    @DeleteMapping
    public String deleteAfterDeadlineTasks(@PathVariable String id, Model model){
        List<Task> afterDeadlineTasks = afterDeadlineTaskService.removeAfterDeadlineTasks(id);
        model.addAttribute("tasks", afterDeadlineTasks);
        return "afterdeadlinetasks.html";
    }
}

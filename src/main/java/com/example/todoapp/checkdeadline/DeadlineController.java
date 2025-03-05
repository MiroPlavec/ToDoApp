package com.example.todoapp.checkdeadline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@Controller
@RequestMapping("/deadline")
public class DeadlineController {

    private final DeadlineRepositry deadlineRepositry;

    @Autowired
    public DeadlineController(DeadlineRepositry deadlineRepositry) {
        this.deadlineRepositry = deadlineRepositry;
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void setTaskDeadline(){
        LocalDate today = LocalDate.now();
        deadlineRepositry.checkDeadlinesDate(today.toString());
    }
}

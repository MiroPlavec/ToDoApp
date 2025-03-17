package com.example.todoapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogOutController {

    private final LogInService logInService;

    public LogOutController(LogInService logInService) {
        this.logInService = logInService;
    }

    @GetMapping
    public String logOut(){
        logInService.logOut();

        return "redirect:/";
    }
}

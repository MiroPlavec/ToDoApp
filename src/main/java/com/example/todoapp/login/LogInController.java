package com.example.todoapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class LogInController {

    private final LogInService logInService;

    public LogInController(LogInService logInService) {
        this.logInService = logInService;
    }

    @GetMapping
    public String showLogInScreen(){
        if(logInService.isLogged()){
            return "redirect:/tasks";
        }
        return "login.html";
    }

    @PostMapping
    public String logIn(@RequestBody LoginRequest loginRequest){
        boolean isLogged = logInService.logIn(loginRequest.getUsername(), loginRequest.getPassword());
        if(isLogged){
            return "redirect:/tasks";
        }
        return "login.html";
    }
}

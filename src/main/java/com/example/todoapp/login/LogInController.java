package com.example.todoapp.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("")
public class LogInController {

    private final static String INVALID_CREDENTIAL = "Please enter valid credential";

    private final LogInService logInService;

    public LogInController(LogInService logInService) {
        this.logInService = logInService;
    }

    @GetMapping
    public String showLogInScreen(){
        if(logInService.isLogged()){
            return "redirect:/home";
        }
        return "login.html";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Map<String, String>> logIn(@RequestBody LoginRequest loginRequest){
        boolean isLogged = logInService.logIn(loginRequest.getUsername(), loginRequest.getPassword());
        if(isLogged){
            // send user path when to redirect if credential are correct
            return ResponseEntity.ok(Collections.singletonMap("redirect", "/home"));
        }
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap("redirect", LogInController.INVALID_CREDENTIAL));
    }
}

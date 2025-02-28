package com.example.todoapp.login;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class LogInService {

    private boolean isLogged = false;
    private final String username = "root";
    private final String password = "root";


    public boolean logIn(String username, String password){
        if(this.username.equals(username) && this.password.equals(password)){
            isLogged = true;
        }
        return isLogged;
    }

    public boolean isLogged() {
        return isLogged;
    }
}

package com.example.todoapp.login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class LogInService {

    private boolean isLogged = false;

    @Value("${custom.login.username}")
    private String username;

    @Value("${custom.login.password}")
    private String password;


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

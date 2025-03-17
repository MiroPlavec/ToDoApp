package com.example.todoapp.login;

import com.fasterxml.jackson.annotation.JsonCreator;

public class LoginRequest {
    private String username;
    private String password;

    @JsonCreator
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }
}

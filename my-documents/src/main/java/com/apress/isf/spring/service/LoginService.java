package com.apress.isf.spring.service;

import com.apress.isf.java.service.Login;

public class LoginService implements Login {

    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAuthorized(String email, String password) {
        return username.equals(email) && this.password.equals(password);
    }
}

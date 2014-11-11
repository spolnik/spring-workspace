package com.apress.isf.java.service;

public interface Login {
    boolean isAuthorized(String email, String password);
}

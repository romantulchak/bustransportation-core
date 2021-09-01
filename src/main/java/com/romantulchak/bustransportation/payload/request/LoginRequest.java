package com.romantulchak.bustransportation.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Username cannot be null or empty")
    private String username;

    @NotBlank(message = "User password cannot be null or empty")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

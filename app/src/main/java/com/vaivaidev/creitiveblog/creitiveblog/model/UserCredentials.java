package com.vaivaidev.creitiveblog.creitiveblog.model;

/**
 * Created by Iva on 3/8/2018.
 * This class is used for packing user and password
 * required by retrofit.
 */

public class UserCredentials {

    private String email;
    private String password;

    public UserCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

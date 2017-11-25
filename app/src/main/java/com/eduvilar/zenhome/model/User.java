package com.eduvilar.zenhome.model;

/**
 * Created by efraespada on 25/11/2017.
 */

public class User {

    private String name;
    private String email;
    private String password;
    private Boolean notificationsEnabled;

    public User() {
        // nothing to do here
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNotificationsEnabled() {
        return notificationsEnabled == null || !notificationsEnabled ? !notificationsEnabled : notificationsEnabled;
    }

    public void setNotificationsEnabled(boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

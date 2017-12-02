package com.eduvilar.zenhome.model;

import java.util.List;

/**
 * Created by efraespada on 25/11/2017.
 */

public class User {

    private String name;
    private String email;
    private String photoURL;
    private String password;
    private Boolean notificationsEnabled;
    private List<House> houses;

    public User() {
        // nothing to do here
    }

    public User(String email, String password, String photoURL) {
        this.email = email;
        this.photoURL = photoURL;
        this.password = password;
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

    public String getPhotoURL() {
        return photoURL;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public void addHouse(House house) {
        houses.add(house);
    }
}

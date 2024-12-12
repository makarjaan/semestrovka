package ru.kpfu.itis304.entity;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String profilePhotoUrl;

    public User(int id, String name, String email, String phone, String password, String profilePhotoUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public User(String name, String email, String phone, String password, String profilePhotoUrl) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.profilePhotoUrl = profilePhotoUrl;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }
}

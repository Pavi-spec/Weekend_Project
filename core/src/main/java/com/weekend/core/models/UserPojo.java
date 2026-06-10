package com.weekend.core.models;

public class UserPojo {

    private String name;
    private String email;
    private String phone;

    public UserPojo(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
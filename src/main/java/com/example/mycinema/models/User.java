package com.example.mycinema.models;

import com.example.mycinema.Enums.EnumRoles;

public class User {
    public String name;
    public String email;
    public String password;
    public EnumRoles role;

    public User(String name, String email, String password, EnumRoles role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}

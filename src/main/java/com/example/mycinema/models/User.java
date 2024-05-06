package com.example.mycinema.models;

import com.example.mycinema.Enums.EnumRoles;

public abstract class User {
    public String name;
    public String email;
    public String password;
    public EnumRoles role;

    public User(String email, String password, EnumRoles role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

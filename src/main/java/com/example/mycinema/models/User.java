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

    public User(String name, String email, String password, EnumRoles role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EnumRoles getRole() {
        return role;
    }

    public void setRole(EnumRoles role) {
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

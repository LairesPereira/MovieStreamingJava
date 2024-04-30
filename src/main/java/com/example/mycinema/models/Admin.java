package com.example.mycinema.models;

import com.example.mycinema.Enums.EnumRoles;

public class Admin extends User {
    public Admin(String name, String email, String password, EnumRoles role) {
        super(name, email, password, role);
    }
}

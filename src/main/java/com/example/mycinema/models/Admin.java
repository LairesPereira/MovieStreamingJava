package com.example.mycinema.models;

import com.example.mycinema.Enums.EnumRoles;

public class Admin extends User {
    EnumRoles role;

    public Admin(String email, String password, EnumRoles role) {
        super(email, password, role);
    }
}

package com.example.mycinema.models;

import com.example.mycinema.Enums.EnumRoles;

public class Client extends User{
    public Client(String name, String email, String password, EnumRoles role) {
        super(name, email, password, role);
    }
}

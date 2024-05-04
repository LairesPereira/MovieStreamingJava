package com.example.mycinema.models;

import com.example.mycinema.DB.FakeDB;
import com.example.mycinema.Enums.EnumRoles;

public abstract class Session extends FakeDB {
    private Client user;

    public void setUserSession(String email, String password, EnumRoles role, boolean isKidProfile) {
        this.user = new Client(email, password, role, isKidProfile);
    }

    public String sessionGetUserName() {
        return user.name;
    }

    public EnumRoles sessionGetRole() {
        return user.role;
    }

    public Client sessionGetUser() {
        return user;
    }
}

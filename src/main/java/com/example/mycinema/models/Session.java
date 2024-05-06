package com.example.mycinema.models;

import com.example.mycinema.DB.FakeDB;
import com.example.mycinema.Enums.EnumRoles;

public abstract class Session extends FakeDB {
    private Client user;
    private Admin admin;

    public void setUserSession(String email, String password, EnumRoles role, boolean isKidProfile) {
        this.user = new Client(email, password, role, isKidProfile);
    }

    public void setAdminSession(String email, String password, EnumRoles role) {
        this.admin = new Admin(email, password, role);
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

    public Admin sessionGetAdmin() {
        return admin;
    }
}

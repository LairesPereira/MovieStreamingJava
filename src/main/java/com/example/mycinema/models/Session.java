package com.example.mycinema.models;

import com.example.mycinema.Enums.EnumRoles;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class Session extends FakeDB {
    private User user;
    private String username;
    private EnumRoles role;

    public void sessionSetUser(User user) {
        this.user = user;
        this.username = user.name;
        this.role = user.role;
    }

    public String sessionGetUserName() {
        return username;
    }

    public EnumRoles sessionGetRole() {
        return role;
    }

    public User sessionGetUser() {
        return user;
    }
}

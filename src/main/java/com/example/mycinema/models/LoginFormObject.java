package com.example.mycinema.models;

public class LoginFormObject {
    private String email;
    private String password;
    private String kidsProfile;
    private String isAdmin;

    public String isKidsProfile() {
        return kidsProfile;
    }

    public void setKidsProfile(String kidsProfile) {
        this.kidsProfile = kidsProfile;
    }

    public String isAdmin() {
        return isAdmin;
    }

    public void setAdmin(String admin) {
        isAdmin = admin;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

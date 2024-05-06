package com.example.mycinema.models;

import com.example.mycinema.Enums.EnumRoles;

public class Client extends User{
    private boolean isKidProfile;

    public Client(String email, String password, EnumRoles role, boolean isKidProfile){
        super(email, password, role);
        this.isKidProfile = isKidProfile;
    }

    public boolean getIsKidProfile() {
        return isKidProfile;
    }
}


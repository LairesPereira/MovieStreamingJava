package com.example.mycinema.DB;

import com.example.mycinema.Contracts.IFakeDBQueries;
import com.example.mycinema.Enums.EnumRoles;
import com.example.mycinema.models.Client;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class FakeDB implements IFakeDBQueries  {
    ArrayList<Client> users = new ArrayList<>();

    @Override
    public boolean loginUser(String email, String password)  {
        for (Client user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkIfUserExists(String email)  {
        for (Client user : users) {
            if(user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void registerUserDB(String email, String password, EnumRoles role, boolean isKidProfile) {
        users.add(new Client(email, password, EnumRoles.CLIENT, isKidProfile));
    }


    @Override
    public void getUserDB(String email) {}

//    USE IF NECESSARY TO SEE ALL USERS
    public void showAllUseresDB() {
        for (Client user : users) {
            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            System.out.println(user.getIsKidProfile());
        }
    }
}
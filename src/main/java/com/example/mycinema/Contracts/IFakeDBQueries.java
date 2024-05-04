package com.example.mycinema.Contracts;
import com.example.mycinema.Enums.EnumRoles;

import java.io.FileNotFoundException;

public interface IFakeDBQueries {
    boolean loginUser(String email, String password) throws FileNotFoundException;
    boolean checkIfUserExists(String email) throws FileNotFoundException;
    void getUserDB(String email);
    void registerUserDB(String email, String password, EnumRoles role, boolean isKidProfile);
}

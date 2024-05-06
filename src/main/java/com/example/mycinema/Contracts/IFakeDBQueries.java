package com.example.mycinema.Contracts;

import java.io.FileNotFoundException;

public interface IFakeDBQueries {
    boolean loginUser(String email, String password) throws FileNotFoundException;
    boolean checkIfUserExists(String email) throws FileNotFoundException;
}

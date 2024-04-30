package com.example.mycinema.models;

import com.example.mycinema.Enums.EnumRoles;

public abstract class FakeDB {
    private User[] usersDB = new User[1];

    public boolean checkIfUserExists(String email, String password) {
        if (usersDB[0] != null) {
            for (User user : usersDB) {
                if(user.email.equals(email) && user.password.equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void registerUserDB(String name, String email, String password, EnumRoles role) {
        User newUser = new Client(name, email, password, role);

        User aux[] = usersDB.clone();
        usersDB = new User[aux.length];

        for(int i = 0; i < usersDB.length; i++ ) {
            usersDB[i] = aux[i];
        }

        usersDB[usersDB.length - 1] = newUser;
    }

    public User getUserDB(String email) {
        User userFound = null;
        for (User user: usersDB) {
            if(user.email.equals(email)) {
                userFound = user;
            }
        }
        return userFound;
    }
}

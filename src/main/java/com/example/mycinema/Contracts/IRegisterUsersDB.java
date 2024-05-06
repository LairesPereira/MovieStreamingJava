package com.example.mycinema.Contracts;

import com.example.mycinema.Enums.EnumRoles;

public interface IRegisterUsersDB {
   void registerUserDB(String email, String password, EnumRoles role, boolean isKidProfile);
   void registerAdminUserDB(String email, String password, EnumRoles role);
}

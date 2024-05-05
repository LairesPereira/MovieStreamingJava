package com.example.mycinema.Controllers;

import com.example.mycinema.Enums.EnumRoles;
import com.example.mycinema.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Home extends Session {

    @GetMapping("/browse")
    public String home(Model model) {
        boolean isKidProfile = sessionGetUser().getIsKidProfile();

        Cinema cinema = new Cinema();

        model.addAttribute("moviesList", cinema.movies);
        model.addAttribute("isKidProfile", isKidProfile);

        return "index";
    }

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("loginFormObject", new LoginFormObject());
        return "login";
    }

    @PostMapping("/")
    public String auth(@ModelAttribute LoginFormObject loginFormObject, Model model) {
        String userEmail = loginFormObject.getEmail();
        String userPassword = loginFormObject.getPassword();
        boolean isKidProfile = loginFormObject.isKidsProfile() != null;
        boolean login = false;

        if (super.checkIfUserExists(userEmail)) {
            if (super.loginUser(userEmail, userPassword)) {
                login = true;
            }
        }  else {
            if(userEmail != null && userPassword != null) {
                super.registerUserDB(userEmail, userPassword, EnumRoles.CLIENT, isKidProfile);
                login = true;
            }
        }

        if(login) {
            super.setUserSession(userEmail, userPassword, EnumRoles.CLIENT, isKidProfile);
            return "redirect:browse";
        }
        return "login";
    }
}

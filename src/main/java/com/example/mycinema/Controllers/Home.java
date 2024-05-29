package com.example.mycinema.Controllers;

import com.example.mycinema.Enums.EnumRoles;
import com.example.mycinema.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Home extends Session {

    private final boolean SKIP_LOGIN = true;

    @Autowired
    Cinema cinema;

    public Home(Cinema cinema) {
        this.cinema = cinema;
    }


    @GetMapping("/admin")
    public String loginAdmin(Model model) {
        model.addAttribute("adminLogin", true);
        model.addAttribute("loginFormObject", new LoginFormObject());
        return "login";
    }

    @PostMapping("/admin")
    public String adminAuth(@ModelAttribute LoginFormObject loginFormObject, Model model) {
        super.registerAdminUserDB("lala@lala", "123123", EnumRoles.ADMIN);
        String userEmail = loginFormObject.getEmail();
        String userPassword = loginFormObject.getPassword();

        if (super.checkAdminExists(userEmail)) {
            super.setAdminSession(userEmail, userPassword, EnumRoles.ADMIN);
            model.addAttribute("moviesList", cinema.movies);
            model.addAttribute("isKidProfile", false);
            model.addAttribute("admin", true);
            return "index";
            }
         else {
            return "redirect:/";
        }
    }

    @GetMapping("/browse")
    public String home(Model model) {
        boolean isKidProfile = false;
        if(!SKIP_LOGIN) {
            isKidProfile = sessionGetUser().getIsKidProfile();
        }
        model.addAttribute("isKidProfile", isKidProfile);
        model.addAttribute("moviesList", cinema.movies);
        model.addAttribute("admin", false);
        return "index";
    }

    @GetMapping("/")
    public String login(Model model) {
        if (SKIP_LOGIN) {
            return "redirect:browse";
        }
        if (cinema.movies.size() == 0) {
            System.err.println("ERRO NO /");
            return "no_movies";
        }
        model.addAttribute("adminLogin", false);
        model.addAttribute("loginFormObject", new LoginFormObject());
        return "insert_path";
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

    @PostMapping("/deleteMovies")
    public String deleteMovies(@RequestParam String movieTitle, Model model) {
        cinema.removeMovie(movieTitle);
        model.addAttribute("moviesList", cinema.movies);
        model.addAttribute("isKidProfile", false);
        model.addAttribute("admin", true);
        return "index";
    }
}

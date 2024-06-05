package com.example.mycinema.Controllers;

import com.example.mycinema.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Home {

    @Autowired
    Cinema cinema;

    public Home(Cinema cinema) {
        this.cinema = cinema;
    }

    @GetMapping("/browse")
    public String home(Model model) {
        model.addAttribute("moviesList", cinema.movies);
        return "index";
    }

    @GetMapping("/")
    public String login(Model model) {
        return "redirect:browse";
    }
}

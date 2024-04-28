package com.example.mycinema.Controllers;

import com.example.mycinema.models.Cinema;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {
    @GetMapping("/")
    public String home(Model model) {
        Cinema cinema = new Cinema();
        model.addAttribute("moviesList", cinema.movies);
        return "index";
    }
}

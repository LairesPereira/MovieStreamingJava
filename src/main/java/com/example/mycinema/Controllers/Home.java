package com.example.mycinema.Controllers;

import com.example.mycinema.Enums.EnumRoles;
import com.example.mycinema.models.Cinema;
import com.example.mycinema.models.Movie;
import com.example.mycinema.models.Session;
import com.example.mycinema.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Scanner;

@Controller
public class Home extends Session {
    @GetMapping("/")
    public String home(Model model) {
        // just to meet classroom requirements
//        login();
        registerUserDB("laires", "lala", "123", EnumRoles.CLIENT);
        sessionSetUser(new User("laires", "lala", "123", EnumRoles.CLIENT));

        // starts real project
        Cinema cinema = new Cinema();
        model.addAttribute("moviesList", cinema.movies);
        model.addAttribute("role", sessionGetUser().role.toString());

        return "index";
    }

    // just to meet classroom requirements
    public void login() {
        while (true) {
            System.out.println("---------------------");
            System.out.println("Digite 1 para login, 2 para cadastro ou 3 para sair: ");
            Scanner scan = new Scanner(System.in);
            String userChoice = scan.next();
            System.out.println(userChoice);
            if(userChoice.equals("3")) { break; }
            if (userChoice.equals("1")) {
                System.out.println("Digite seu email: ");
                scan = new Scanner(System.in);
                String userEmail = scan.nextLine();
                System.out.println("Digite sua senha: ");
                scan = new Scanner(System.in);
                String password = scan.nextLine();
                if(checkIfUserExists(userEmail, password)) {
                    User user = getUserDB(userEmail);
                    sessionSetUser(new User(user.name, user.email, user.password, user.role));
                } else {
                    System.err.println("Usuario ou senha inválidos");
                }
            } else if (userChoice.equals("2")) {
                System.out.println("\nDigite seu nome: ");
                scan = new Scanner(System.in);
                String name = scan.next();
                System.out.println("\nDigite seu email: ");
                scan = new Scanner(System.in);
                String email = scan.next();
                System.out.println("Digite sua senha: ");
                scan = new Scanner(System.in);
                String password = scan.next();
                System.out.println("Digite 1 para admin ou 2 para cliente: ");
                scan = new Scanner(System.in);
                String role = scan.next();

                if (checkIfUserExists(email, password)) {
                    System.err.println("Email ja cadastrado!");
                } else {
                    if (role.equals("1")) {
                        registerUserDB(name, email, password, EnumRoles.ADMIN);
                        sessionSetUser(new User(name, email, password, EnumRoles.ADMIN));
                    } else {
                        registerUserDB(name, email, password, EnumRoles.CLIENT);
                        sessionSetUser(new User(name, email, password, EnumRoles.CLIENT));
                    }
                }
            }
            System.out.println("---------------------");
        }
    }
}

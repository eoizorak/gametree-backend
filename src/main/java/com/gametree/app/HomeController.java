package com.gametree.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Injeção de Dependência: O Spring nos dá o repository pronto
    private final ProfileRepository repository;

    public HomeController(ProfileRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Busca o PRIMEIRO perfil que encontrar no banco (ID 1)
        // O .orElse(null) é para não quebrar se o banco estiver vazio
        Profile perfilDoBanco = repository.findAll().stream().findFirst().orElse(null);

        if (perfilDoBanco != null) {
            model.addAttribute("profile", perfilDoBanco);
            return "index";
        } else {
            return "erro"; // Só por segurança
        }
    }
}
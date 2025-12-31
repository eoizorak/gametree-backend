package com.gametree.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        // Simulando dados que viriam do Banco de Dados
        Profile perfil = new Profile(
            "Matheus Dev Java & Backend",
            "Criador do GameTree | Backend Dev",
            "https://ui-avatars.com/api/?name=Matheus+Java&background=00ff88&color=000",
            "🔴 Manutenção (Voltamos às 14h)", 
            "https://discord.gg/seu-link",
            "https://youtube.com/seu-canal",
            "https://instagram.com/seu-insta"
        );

        // Enviando o perfil para o HTMLL
        model.addAttribute("profile", perfil);
        
        return "index";
    }
}
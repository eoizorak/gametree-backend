package com.gametree.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final ProfileRepository repository;

    public HomeController(ProfileRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String home(Model model) {

        Profile perfil = repository.findAll().stream().findFirst().orElse(new Profile());
        

        if (perfil.getNome() == null) perfil.setNome("Seu Nome Aqui");
        if (perfil.getBio() == null) perfil.setBio("Sua bio aparecerá aqui");
        
        model.addAttribute("profile", perfil);
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {

        Profile perfil = repository.findAll().stream().findFirst().orElse(new Profile());
        model.addAttribute("profile", perfil);
        return "admin";
    }

    @PostMapping("/admin/save")
    public String salvar(@ModelAttribute Profile profile) {

        Profile existente = repository.findAll().stream().findFirst().orElse(null);
        if (existente != null) {
            profile.setId(existente.getId()); 
        }
        
        repository.save(profile);
        return "redirect:/";
    }
}
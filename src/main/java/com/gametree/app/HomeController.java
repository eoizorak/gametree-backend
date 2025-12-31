package com.gametree.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final ProfileRepository profileRepository;
    private final LinkRepository linkRepository; 

    public HomeController(ProfileRepository profileRepository, LinkRepository linkRepository) {
        this.profileRepository = profileRepository;
        this.linkRepository = linkRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        Profile perfil = profileRepository.findAll().stream().findFirst().orElse(new Profile());
        if (perfil.getNome() == null) perfil.setNome("Seu Nome Aqui");
        if (perfil.getBio() == null) perfil.setBio("Sua bio aparecerá aqui");
        model.addAttribute("profile", perfil);
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        Profile perfil = profileRepository.findAll().stream().findFirst().orElse(new Profile());
        model.addAttribute("profile", perfil);
        return "admin";
    }

    @PostMapping("/admin/save")
    public String salvar(@ModelAttribute Profile profile) {
        Profile existente = profileRepository.findAll().stream().findFirst().orElse(null);
        if (existente != null) {
            profile.setId(existente.getId()); 
            profile.setLinks(existente.getLinks()); 
        }
        profileRepository.save(profile);
        return "redirect:/admin"; 
    }

    

    @PostMapping("/admin/links/add")
    public String adicionarLink(@RequestParam String titulo, @RequestParam String url) {
        Profile perfil = profileRepository.findAll().stream().findFirst().orElse(null);
        if (perfil != null) {
            
            Link novoLink = new Link(titulo, url, perfil);
            linkRepository.save(novoLink);
        }
        return "redirect:/admin";
    }

@PostMapping("/admin/links/delete/{id}")
    public String deletarLink(@PathVariable Long id) {
        
        Link link = linkRepository.findById(id).orElse(null);
        
        if (link != null) {
            
            Profile dono = link.getProfile();
            
            if (dono != null) {
                dono.getLinks().removeIf(l -> l.getId().equals(id));
                profileRepository.save(dono); 
            }

            linkRepository.delete(link);
        }
        
        return "redirect:/admin";
    }
}
package com.gametree.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects; 

@Controller
public class HomeController {

    private final ProfileRepository profileRepository;
    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    public HomeController(ProfileRepository profileRepository, LinkRepository linkRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }

    
    @GetMapping("/u/{username}")
    public String verPerfilPublico(@PathVariable String username, Model model) {
        User usuario = userRepository.findByUsername(username).orElse(null);
        
        if (usuario != null && usuario.getProfile() != null) {
            model.addAttribute("profile", usuario.getProfile());
            return "index";
        }
        
        return "redirect:/login";
    }

    
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        User usuarioLogado = userRepository.findByUsername(principal.getName()).orElseThrow();
        model.addAttribute("profile", usuarioLogado.getProfile());
        return "admin";
    }

    @PostMapping("/admin/save")
    public String salvar(@ModelAttribute Profile profileForm, Principal principal) {
        User usuarioLogado = userRepository.findByUsername(principal.getName()).orElseThrow();
        Profile perfilDoBanco = usuarioLogado.getProfile();

        perfilDoBanco.setNome(profileForm.getNome());
        perfilDoBanco.setBio(profileForm.getBio());
        perfilDoBanco.setAvatarUrl(profileForm.getAvatarUrl());
        perfilDoBanco.setStatusServidor(profileForm.getStatusServidor());

        profileRepository.save(perfilDoBanco);
        return "redirect:/admin";
    }

    @PostMapping("/admin/links/add")
    public String adicionarLink(@RequestParam String titulo, @RequestParam String url, Principal principal) {
        User usuarioLogado = userRepository.findByUsername(principal.getName()).orElseThrow();
        Profile perfil = usuarioLogado.getProfile();

        Link novoLink = new Link(titulo, url, perfil);
        linkRepository.save(novoLink);

        return "redirect:/admin";
    }

    @PostMapping("/admin/links/delete/{id}")
    public String deletarLink(@PathVariable Long id, Principal principal) {
        Link link = linkRepository.findById(id).orElse(null);
        
        
        if (link != null && link.getProfile().getUser().getUsername().equals(principal.getName())) {
            
            Profile dono = link.getProfile();
            
            
            dono.getLinks().removeIf(l -> Objects.equals(l.getId(), id));
            
            profileRepository.save(dono);
            linkRepository.delete(link);
        }
        
        return "redirect:/admin";
    }
}